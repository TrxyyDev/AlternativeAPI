![Logo](https://nsa40.casimages.com/img/2020/05/07/200507103021373167.png)

<h4 align="center">A JavaFX Library for Minecraft Launchers. Works with Forge 1.13+!</h4>

## Downloads

- Download latest version [HERE](https://github.com/TrxyyDev/AlternativeAPI/releases/latest)
- You can use my launcher sources [HERE](https://github.com/TrxyyDev/AlternativeAPI-launcher)
- Web folder [HERE](https://mega.nz/file/q6h3QbKR#Alm6P2DKfPorcjIv6JfT2OcMHOs4fGnbHDIffA_YAzo)
- Resources (for launcher) [HERE](https://mega.nz/file/ProWSSoZ#IzHCIfJsTbhty-U6GYSqlKQG7Dg3LBfbc6-FgA6Devw)

## Forge 1.13+ (deleting libraries)

```
Go inside your libraries folder in your host.
Go to libraries/com/google/guava/guava/
And keep only the higher version, delete all others.
## OR
Go inside your delete.cfg folder in your host
and write a new line by putting the location of the guava libraries
which are lower than the highest

Example:
libraries/com/google/guava/guava/15.0/guava-15.0.jar
libraries/com/google/guava/guava/20.0/guava-20.0.jar
libraries/com/google/guava/guava/21.0/guava-21.0.jar

```
## Distants files

- ignore.cfg
```
bin/game/servers.dat
bin/game/options.txt
bin/game/optionsshaders.txt
bin/game/optionsof.txt
bin/game/usercache.json
bin/launcher_config.json
bin/game/screenshots/
bin/game/saves/
bin/game/resourcepacks/
bin/game/shaderpacks/
bin/game/config/
bin/game/mods/
logs/
```
- delete.cfg
```
libraries/com/google/guava/guava/15.0/guava-15.0.jar
```



You can use NodeJS for better performances to check custom files
- https://github.com/chaun14/AlternativeApi-NodeJS-Server-lite
- https://github.com/chaun14/AlternativeApi-NodeJS-Server-full
