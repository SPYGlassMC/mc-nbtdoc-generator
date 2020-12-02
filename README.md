# NBTDOC-genmod

A [Fabric][fabric] mod to generate item data for [Yurihaia][yurihaia]'s [mc-nbtdoc repository][mc-nbtdoc].

## Getting Started

- Replace several variables:
   - `./src/main/java/yurihaia/rd/mixin/HijackMain.java`:
      - Replace `%MAPPINGS_FILE_PATH%` with the absolute path to [mc-nbtdoc repository][mc-nbtdoc]'s `./generate/mappings.json` file.
      - Replace `%GENERATED_DIR_PATH%` with the absolute path to [mc-nbtdoc repository][mc-nbtdoc]'s `./minecraft/generated` directory.
   - `./gradle.properties`:
      - Replace `%GAME_VERSION%` with a Minecraft `version` shown in [fabric's version API][fabric-version]. e.g. `20w49a`.
      - Replace `%MAPPINGS_VERSION%` with a yarn mapping `version` shown in [fabric's version API][fabric-version]. e.g. `20w49a+build.1`.
      - Replace `%LOADER_VERSION%` with a fabric loader `version` shown in [fabric's version API][fabric-version]. e.g. `0.10.8`.
      - Replace `%FABRIC_VERSION%` with a fabric API version shown in [fabric's maven metadata][fabric-maven]. e.g. `0.27.2+1.17`.
      - Note: if you're a human, you can get those versions easily at [here][modmuss-fabric].
- Execute the generator mod by running `./gradlew runServer`.
- The contents at `%GENERATED_DIR_PATH%` should be updated to your specified `%GAME_VERSION%`.

[fabric]: https://fabricmc.net/
[fabric-maven]: https://maven.fabricmc.net/net/fabricmc/fabric-api/fabric-api/maven-metadata.xml
[fabric-version]: https://meta.fabricmc.net/v1/versions
[mc-nbtdoc]: https://github.com/Yurihaia/mc-nbtdoc
[modmuss-fabric]: https://modmuss50.me/fabric.html
[yurihaia]: https://github.com/Yurihaia
