package hieuapp.tfidf;

import java.io.File;
import java.io.IOException;

public class MainTF_IDF {
	
	public static void main(String[] args){
		Dictionary dictionary = new Dictionary();

//		try {
//			dictionary.doTermFrequency(new File("/home/hieuapp/data_train/bag_word"));
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			dictionary.doIDF(new File("/home/hieuapp/data_train/bag_word"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
