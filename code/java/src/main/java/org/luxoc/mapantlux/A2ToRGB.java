package org.luxoc.mapantlux;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class A2ToRGB {
	final static Logger LOGGER = LogManager.getLogger(A2ToRGB.class.getName());

	static String pathOut = "/home/juju/Bureau/orienteering/lidar/out/lux/";

	public static void main(String[] args) throws Throwable {
		LOGGER.info("Start");

		int xS = 69000, yS = 40000;
		int xE = 77000, yE = 150000;

		//int xS = 62000, yS = 99000;
		//int xE = 65000, yE = 150000;

		//int xS = 61500, yS = 98000;
		//int xE = 70500, yE = 107000;


		LOGGER.info("Get output files");
		Set<String> files = A0Status.getFiles(pathOut);
		LOGGER.info(files.size());

		for(String f : files) {
			if(f.contains(".pgw")) continue;
			if(f.contains(".xml")) continue;
			if(f.contains(".laz.png")) continue;
			if(!f.contains(".png")) {
				System.err.println(f);
				continue;
			}

			//exclude files out of the tile
			String f2 = f.replace(pathOut, "");
			String[] sp = f2.split("_");
			int x_ = Integer.parseInt(sp[2]);
			if(x_<xS) continue;
			if(x_>=xE) continue;
			int y_ = Integer.parseInt(sp[3]);
			if(y_<yS) continue;
			if(y_>=yE) continue;

			int size = (int) Files.size(Paths.get(f));
			if(size == 0) continue;

			//
			LOGGER.info(f);
			String fOut = f.replace(".png", "_rgb.png").replace("/lux/", "/lux_rgb/");
			//LOGGER.info(fOut);

			String cmd = "pct2rgb.py " + f + " " + fOut;
			//System.out.println(cmd);
			A3Merge.run(cmd, true);

			//copy pgw file
			Files.copy(
					(new File(f.replace(".png", ".pgw"))).toPath(),
					(new File(f.replace(".png", "_rgb.pgw").replace("/lux/", "/lux_rgb/"))).toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		}

		LOGGER.info("End");
	}

}
