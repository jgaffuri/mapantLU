# Computing resource estimation

Estimate necessary computing resources for a mapant.lu.

## Disk space

### Input data

Number of zip files to download (from [here](https://map.geoportail.lu/theme/main?version=3&zoom=10&X=721195&Y=6400425&lang=en&layers=1788&opacities=0.75&bgLayer=orthogr_2013_global&crosshair=false&rotation=0&time=)): **1287**

The size of each file is around 300 MB. When unzipped, the files have a similar size. Total size is thus around 390 GB

It is however not necessary to download all the files at once - a moving window can be used, and only the necessary data is downloaded on-the-fly, processed, and then deleted. A maximum of 3x3= 9 zip files is necessary, which represents less than 3 GB.

### Output data

The output of each tile is around 50 MB, most of this space is occupied by dxf files which are not necessary to keep. Only png images can be kept. There size is around 250 kB per tile. There are 9 tiles per zip file, so in total **11500 tiles**. The disk space is: 11500x250kB = less that 3 GB.

In conclusion: **Around 6 GB disk space is necessary**. So, disk space is not a problem.

## Computing time

There are 11500 tiles to process. The process of each tile takes around 15' (rough estimation, with the 64 bit version of the program - it was 40' with the 32 bit. It seems this computing time depends very much on the geography: It takes longer in steep, complex areas, than in flat and empty areas). In total, around 120 days.

With 3 processors, this duration can be reduced to 3 times less: **40 days**.

That's a lot but not unrealistic.

## Improvement

To improve this:
- select settings that may speedup the process duration.
- use more processors, faster. AWS ?
- reduce area - maybe with a focus on most interesting zones (mullertall, red rocks, forests, etc.)
