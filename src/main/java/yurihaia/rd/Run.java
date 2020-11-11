package yurihaia.rd;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.MappingResolver;
import net.minecraft.MinecraftVersion;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Run {
	public static void run(String mappingspath, String outpath) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		JsonObject mappings = gson.fromJson(new FileReader(mappingspath), JsonObject.class);
		Identifier[] regs = {
			new Identifier("item")
		};
		ImmutableMap<Identifier, String> ident2target = ImmutableMap.of(regs[0], "minecraft:item");
		MappingResolver res = FabricLoader.getInstance().getMappingResolver();
		PrintWriter mod = new PrintWriter(new FileWriter(new File(outpath + "/mod.nbtdoc")));
		mod.printf("// Generated info for Minecraft %s\n", MinecraftVersion.create().getName());
		for(Identifier rid: regs) {
			mod.printf("mod %s;\n", rid.getPath());
			JsonObject map = mappings.get(rid.toString()).getAsJsonObject();
			HashMap<String, List<Identifier>> name2id = new HashMap<>();
			
			Registry<?> reg = Registry.REGISTRIES.get(rid);
			for(Identifier id: reg.getIds()) {
				Object obj = reg.get(id);
				String mapname;
				try {
					mapname = res.unmapClassName("intermediary", obj.getClass().getName());
				} catch(IllegalArgumentException e) {
					mapname = obj.getClass().getName();
				}
				String name;
				try {
					JsonElement nm = map.get(mapname);
					if(nm.isJsonNull()) {
						continue;
					} else {
						name = nm.getAsString();
					}
				} catch(NullPointerException e) {
					System.err.printf(
						"No mapping for %s (named %s) in registry %s!\n",
						id,
						mapname,
						rid
					);
					continue;
				}
				name2id.putIfAbsent(name, new ArrayList<>());
				name2id.get(name).add(id);
			}
			
			Files.createDirectories(Paths.get(outpath));
			PrintWriter out = new PrintWriter(
				new FileWriter(new File(outpath + "/" + rid.getPath() + ".nbtdoc"))
			);
			for(Map.Entry<String, List<Identifier>> ent: name2id.entrySet()) {
				out.printf("::minecraft::%s describes %s[\n", ent.getKey(), ident2target.get(rid));
				
				Iterator<Identifier> iter = ent.getValue().iterator();
				while(iter.hasNext()) {
					out.printf("\t%s", iter.next().toString());
					if(iter.hasNext()) out.print(',');
					out.println();
				}
				out.println("];");
			}
			out.flush();
			out.close();
		}
		mod.close();
	}
}
