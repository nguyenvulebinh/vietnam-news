package com.hieuapp.vienamnews;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;

public class VietnamNews {
	// input folder = /media/hieuapp/Data/CNTT/vietnam-news/data-train/Dân trí
	static String currentLabel = "";
	static String output = "/home/hieuapp/vitk-input/vietbao/";

	public static void main(String[] args) {
		preprocessContent("/home/hieuapp/ugly-data/vietbao");
	}

	public static void preprocessContent(String pathToData) {
		File inputDir = new File(pathToData);

		// khoi tao folder Dan_Tri
		File outPutDir = new File(output);
		if (!outPutDir.exists()) {
			outPutDir.mkdirs();
			System.out.println("Folder contain labels is created");
		}

		File[] labels = inputDir.listFiles();
		for (int i = 0; i < labels.length; i++) {
			if (labels[i].isDirectory()) {
				
				String folderLabel = labels[i].getName();
				folderLabel = folderLabel.replace(" ", "_");
				
				File outputLabel = new File(output + folderLabel);
				if (!outputLabel.exists()) {
					outputLabel.mkdirs();
					System.out.println("Created folder " + labels[i].getName());

				}
				currentLabel = folderLabel;
				try {
					browseRecursiveFile(labels[i], folderLabel);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void browseRecursiveFile(File dir, String folderLabel) throws IOException {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				browseRecursiveFile(files[i], folderLabel);
			} else {
				String outputName = files[i].getName();
				outputName = outputName.replace(".json", "");
				File dest = new File(output + folderLabel + "/" + outputName);
				editAndSaveAsContent(files[i], dest);
				System.out.println("Done. edited and save as" + files[i].getName());
			}
		}
	}

	public static void editAndSaveAsContent(File source, File dest) throws IOException {
		InputStream is = null;
		OutputStream os = null;
		try {
			String content = FileUtils.readFileToString(source);
			JSONObject jsonContent = new JSONObject(content);
			content = jsonContent.getString("content");
			if (content.length() < 3) {
				content = jsonContent.getString("title");
			}
			content = content.toLowerCase();
			content = content.replaceAll("[,.?!@#$^*-=+():;%~&]", " ");

			InputStream stream = new ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
			is = stream;
			os = new FileOutputStream(dest);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
		} finally {
			is.close();
			if (os != null) {
				os.close();
			}

		}
	}

}
