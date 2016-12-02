/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hieuapp.tfidf;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author
 */
public class Dictionary {
    private Map <String, Integer> inverseWordMap = new HashMap<String, Integer>();
    
    public Dictionary(){
	    
    }
    
    /**
     * Tinh TF cua toan bo van ban
     * @param rootFoler
     * @throws IOException
     */
    public void doTermFrequency(File rootFoler) throws IOException{
    	File tfFile = new File(rootFoler.getAbsolutePath() + "/_tf.txt");
    	int numberDocs = 0;
    	
    	if(tfFile.exists()){
    		tfFile.delete();
    	}
    	tfFile.createNewFile();
		System.out.println("Create tf file: !"+tfFile.getAbsolutePath());
		
    	FileWriter fw =  new FileWriter(tfFile, true);
    	File[] childFolder = rootFoler.listFiles();
    	    	
    	for(File item : childFolder){
    		if(item.isDirectory()){
    			File[] news = item.listFiles();
    			System.out.println("Reading Folder: "+item.getAbsolutePath());
    			for(File f : news){
    				
    				System.out.println("Reading file: "+f.getAbsolutePath());
    				String content = FileUtils.readFileToString(f, "UTF-8");
    				
    				if(content == null || content.length() == 0 || content.equals("")){
    					continue;
    				}
    				
    				String[] words = content.split(" |\n");
    				Map<String, Integer> wordMap = new HashMap<String, Integer>(); 
    				for(String word : words){
    					if(word.length() < 3){
    	                    continue;
    	                }
    					
    					if(wordMap.get(word) != null){
	                		int count = wordMap.get(word);
	                		count++;
	                		wordMap.put(word, count);
	                		
	                	  	                		
	                	}else{
	                		wordMap.put(word, 1);
	                		increaseDocs(word);
	                	}
    	            }
    				
    				Set<String> keys = wordMap.keySet();
    				int size = keys.size();
    				String bagWord = "";
    				String tfWord = "";
    				for(String key : keys){
    					bagWord += key + " ";
    					tfWord += wordMap.get(key) + " ";
    				}
    				
    				//them nhan vao cuoi moi dong
    				bagWord += item.getName();
    				
    				StringBuilder tfContent = new StringBuilder();
    				tfContent.append(size).append("\n").append(bagWord).append("\n").append(tfWord).append("\n");
    				
    				fw.write(tfContent.toString());
    				numberDocs++;
    				    				
    			}
    		}
    	}
    	
    	saveInverseWord(inverseWordMap);
    	
    	fw.close();
    	File oldTF = new File(rootFoler.getAbsolutePath() + "/_tf.txt");
    	File newTF = new File(rootFoler.getAbsolutePath() + "/tf.txt");
    	String tfContent = FileUtils.readFileToString(oldTF);
    	tfContent = numberDocs + "\n" + tfContent;
    	FileWriter writer = new FileWriter(newTF);
    	writer.write(tfContent);
    	writer.close();  	
    	
        System.out.println("Done!");
        return;
    }
    
    private void increaseDocs(String word){
    	
        if(inverseWordMap.get(word) != null){
        	int num = inverseWordMap.get(word);
        	num++;
        	inverseWordMap.put(word, num);
        }else{
        	inverseWordMap.put(word, 1);
        }
    }
    
    private void saveInverseWord(Map<String, Integer> mapWord) throws IOException{
    	File inverseWord = new File("/home/hieuapp/data_train/bag_word/inverseWord.txt");
    	if(inverseWord.exists()){
    		inverseWord.delete();
    	}
    	StringBuilder stringContent = new StringBuilder();
    	StringBuilder intContent = new StringBuilder();
    	
    	inverseWord.createNewFile();
    	Set<String> keys = mapWord.keySet();
    	
    	for(String word : keys){
    		stringContent.append(word).append(" ");
    		int number = mapWord.get(word);
    		intContent.append(number).append(" ");
    	}
    	
    	try {
            FileWriter writer = new FileWriter(inverseWord.getAbsoluteFile());
        	BufferedWriter bw = new BufferedWriter(writer);
        	int numberOfWord = mapWord.size();
        	String content = numberOfWord + "\n" + stringContent + "\n" + intContent;
            bw.write(content);
            bw.close();
            System.out.println("Done! update inverseWord file!");
        } catch (IOException ex) {
            Logger.getLogger(MainTF_IDF.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    }
    
    private Map<String, Integer> loadDictionary() throws IOException{   	 
    	 String line;
    	 
    	 InputStream fis = new FileInputStream("/home/hieuapp/data_train/bag_word/inverseWord.txt");
	     InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
	     BufferedReader br = new BufferedReader(isr);
	 
	     String[] listWord = new String[]{};
	     String[] numberDocs = new String[]{};
	     int numWords = 0;
	     if((line = br.readLine()) != null){
	    	 numWords = Integer.valueOf(line.trim());
	    	 
	     }
	     
	     if((line = br.readLine()) != null){
	    	 listWord = line.split(" ");
	    	 
	     }
	     
	     if((line = br.readLine()) != null){
	    	 numberDocs = line.split(" ");
	    	 
	     }  
	        
	     if(numWords == listWord.length && numWords == numberDocs.length){
	    	 System.out.println("Load dictionary successfull!");
	    	 Map<String, Integer> mapInverseWord = new HashMap<>();
	    	 for(int i = 0; i < numWords; i++){
	    		 mapInverseWord.put(listWord[i], Integer.valueOf(numberDocs[i]));
	    	 }
//	    	 System.out.println(mapInverseWord.toString());
	    	 return mapInverseWord;
	     }
	     
	     return null;
    	 
    }
    
    public void doIDF(File root) throws IOException{
    	File idfFile = new File(root.getAbsolutePath() + "/idf.txt");
    	
    	int numberDocs = getNumberDocs();
    	
    	if(numberDocs == 0){
    		System.out.println("doIDF()::Error when load tf file!");
    		throw new FileNotFoundException();
    	}
    	
    	if(idfFile.exists()){
    		idfFile.delete();
    	}
    	idfFile.createNewFile();
		System.out.println("doIDF()::Create tf file: !"+idfFile.getAbsolutePath());
		
    	Map<String , Integer> mapWordToNumberDocs = loadDictionary();
    	
    	StringBuilder strDict = new StringBuilder();
    	StringBuilder strIDF = new StringBuilder();
    	Set<String> words = mapWordToNumberDocs.keySet();
    	for(String word : words){
    		strDict.append(word).append(" ");
    		int numDocsContainWord = mapWordToNumberDocs.get(word);
    		
    		double idf = (double)numberDocs/(1 + numDocsContainWord);
        	idf =  Math.round(Math.log(idf) * 100.0) / 100.0;
    		strIDF.append(idf).append(" ");
    	}
    	
    	FileWriter fw = new FileWriter(idfFile);
    	fw.write(words.size() + "\n" + strDict + "\n"+strIDF);
    	fw.close();
    	System.out.println("Done cumpute IDF.");
    }

    
    private int getNumberDocs() throws NumberFormatException, IOException{
   	   	 
   	 	InputStream fis = new FileInputStream("/home/hieuapp/data_train/bag_word/tf.txt");
   	 	InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
   	 	BufferedReader br = new BufferedReader(isr);
   	 	
   	 	int numberDocs = 0;
   	 	String line;
   	 	if((line = br.readLine()) != null){
   	 		numberDocs = Integer.valueOf(line.trim());
   	 		System.out.println(numberDocs);
   	 	}
   	 	
   	 	return numberDocs;
    	
    }
    
}
