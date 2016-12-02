package com.hieuapp.vienamnews;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class StopWords {
	
	//public static String[] stopwords = {"a", "as", "able", "about", "above", "according", "accordingly", "across", "actually", "after", "afterwards", "again", "against", "aint", "all", "allow", "allows", "almost", "alone", "along", "already", "also", "although", "always", "am", "among", "amongst", "an", "and", "another", "any", "anybody", "anyhow", "anyone", "anything", "anyway", "anyways", "anywhere", "apart", "appear", "appreciate", "appropriate", "are", "arent", "around", "as", "aside", "ask", "asking", "associated", "at", "available", "away", "awfully", "be", "became", "because", "become", "becomes", "becoming", "been", "before", "beforehand", "behind", "being", "believe", "below", "beside", "besides", "best", "better", "between", "beyond", "both", "brief", "but", "by", "cmon", "cs", "came", "can", "cant", "cannot", "cant", "cause", "causes", "certain", "certainly", "changes", "clearly", "co", "com", "come", "comes", "concerning", "consequently", "consider", "considering", "contain", "containing", "contains", "corresponding", "could", "couldnt", "course", "currently", "definitely", "described", "despite", "did", "didnt", "different", "do", "does", "doesnt", "doing", "dont", "done", "down", "downwards", "during", "each", "edu", "eg", "eight", "either", "else", "elsewhere", "enough", "entirely", "especially", "et", "etc", "even", "ever", "every", "everybody", "everyone", "everything", "everywhere", "ex", "exactly", "example", "except", "far", "few", "ff", "fifth", "first", "five", "followed", "following", "follows", "for", "former", "formerly", "forth", "four", "from", "further", "furthermore", "get", "gets", "getting", "given", "gives", "go", "goes", "going", "gone", "got", "gotten", "greetings", "had", "hadnt", "happens", "hardly", "has", "hasnt", "have", "havent", "having", "he", "hes", "hello", "help", "hence", "her", "here", "heres", "hereafter", "hereby", "herein", "hereupon", "hers", "herself", "hi", "him", "himself", "his", "hither", "hopefully", "how", "howbeit", "however", "i", "id", "ill", "im", "ive", "ie", "if", "ignored", "immediate", "in", "inasmuch", "inc", "indeed", "indicate", "indicated", "indicates", "inner", "insofar", "instead", "into", "inward", "is", "isnt", "it", "itd", "itll", "its", "its", "itself", "just", "keep", "keeps", "kept", "know", "knows", "known", "last", "lately", "later", "latter", "latterly", "least", "less", "lest", "let", "lets", "like", "liked", "likely", "little", "look", "looking", "looks", "ltd", "mainly", "many", "may", "maybe", "me", "mean", "meanwhile", "merely", "might", "more", "moreover", "most", "mostly", "much", "must", "my", "myself", "name", "namely", "nd", "near", "nearly", "necessary", "need", "needs", "neither", "never", "nevertheless", "new", "next", "nine", "no", "nobody", "non", "none", "noone", "nor", "normally", "not", "nothing", "novel", "now", "nowhere", "obviously", "of", "off", "often", "oh", "ok", "okay", "old", "on", "once", "one", "ones", "only", "onto", "or", "other", "others", "otherwise", "ought", "our", "ours", "ourselves", "out", "outside", "over", "overall", "own", "particular", "particularly", "per", "perhaps", "placed", "please", "plus", "possible", "presumably", "probably", "provides", "que", "quite", "qv", "rather", "rd", "re", "really", "reasonably", "regarding", "regardless", "regards", "relatively", "respectively", "right", "said", "same", "saw", "say", "saying", "says", "second", "secondly", "see", "seeing", "seem", "seemed", "seeming", "seems", "seen", "self", "selves", "sensible", "sent", "serious", "seriously", "seven", "several", "shall", "she", "should", "shouldnt", "since", "six", "so", "some", "somebody", "somehow", "someone", "something", "sometime", "sometimes", "somewhat", "somewhere", "soon", "sorry", "specified", "specify", "specifying", "still", "sub", "such", "sup", "sure", "ts", "take", "taken", "tell", "tends", "th", "than", "thank", "thanks", "thanx", "that", "thats", "thats", "the", "their", "theirs", "them", "themselves", "then", "thence", "there", "theres", "thereafter", "thereby", "therefore", "therein", "theres", "thereupon", "these", "they", "theyd", "theyll", "theyre", "theyve", "think", "third", "this", "thorough", "thoroughly", "those", "though", "three", "through", "throughout", "thru", "thus", "to", "together", "too", "took", "toward", "towards", "tried", "tries", "truly", "try", "trying", "twice", "two", "un", "under", "unfortunately", "unless", "unlikely", "until", "unto", "up", "upon", "us", "use", "used", "useful", "uses", "using", "usually", "value", "various", "very", "via", "viz", "vs", "want", "wants", "was", "wasnt", "way", "we", "wed", "well", "were", "weve", "welcome", "well", "went", "were", "werent", "what", "whats", "whatever", "when", "whence", "whenever", "where", "wheres", "whereafter", "whereas", "whereby", "wherein", "whereupon", "wherever", "whether", "which", "while", "whither", "who", "whos", "whoever", "whole", "whom", "whose", "why", "will", "willing", "wish", "with", "within", "without", "wont", "wonder", "would", "would", "wouldnt", "yes", "yet", "you", "youd", "youll", "youre", "youve", "your", "yours", "yourself", "yourselves", "zero"};
	public static String[] stopwords = {"bị",
			  "bởi",
			  "cả","cho",
			  "các",
			  "cái",
			  "cần",
			  "càng",
			  "chỉ","thậm_chí",
			  "chiếc",
			  "cho",
			  "chứ",
			  "chưa",
			  "chuyện",
			  "có",
			  "có_thể",
			  "cứ",
			  "của",
			  "cùng",
			  "cũng",
			  "đã",
			  "đang",
			  "đây",
			  "để",
			  "đến_nỗi",
			  "đều",
			  "điều",
			  "do",
			  "đó",
			  "được",
			  "dưới",
			  "gì",
			  "khi",
			  "không",
			  "là",
			  "lại",
			  "lên",
			  "lúc",
			  "mà",
			  "mỗi",
			  "một_cách",
			  "này",
			  "nên",
			  "nếu",
			  "ngay",
			  "nhiều",
			  "như",
			  "nhưng",
			  "những",
			  "nơi",
			  "nữa",
			  "phải",
			  "qua",
			  "ra","cũng",
			  "rằng",
			  "rất",
			  "rồi",
			  "sau",
			  "sẽ",
			  "so",
			  "sự",
			  "tại",
			  "theo",
			  "thì",
			  "trên",
			  "trước",
			  "từ",
			  "từng",
			  "và",
			  "vẫn",
			  "vào",
			  "vậy",
			  "vì",
			  "việc",
			  "với",
			  "vừa",
			  "nhận",
			  "cao",
			  "nhà",
			  "quá",
			  "riêng",
			  "muốn",
			  "số",
			  "thấy",
			  "hay",
			  "lần",
			  "nào",
			  "bằng",
			  "biết",
			  "lớn",
			  "khác",
			  "thời_gian",
			  "họ",
			  "tháng",
			  "chính",
			  "nói",
			  "đi",
			  "tới",
			  "tôi",
			  "làm",
			  "mới",
			  "ngày",
			  "mình",
			  "còn",
			  "năm",
			  "nhất",
			  "hơn",
			  "ông",
			  "anh",
			  "đến",
			  "người",
			  "ở",
			  "về",
			  "một",
			  "trong",
			  "thực_sự",
			  "ở_trên",
			  "tất_cả",
			  "hầu_hết",
			  "luôn",
			  "giữa",
			  "bất_kỳ",
			  "hỏi",
			  "bạn",
			  "cô",
			  "tớ",
			  "cậu",
			  "bác",
			  "chú",
			  "dì",
			  "thím",
			  "mợ",
			  "bà",
			  "em",
			  "thường",
			  "ai",
			  "mọi",
			  "cảm_ơn",
			  "hoặc",
			  "ngoài_ra",
			  "bây_giờ",
			  "hoàn_toàn",
			  "thì_thôi",
			  "ra_sao","tuy_nhiên"};
	public static Set<String> stopWordSet = new HashSet<String>(Arrays.asList(stopwords));
	public static boolean isStopword(String word) {
		if(word.length() < 2){
			return true;
		}
		if(word.charAt(0) >= '0' && word.charAt(0) <= '9'){
			return true; //remove numbers, "25th", etc
		}
		if(stopWordSet.contains(word)){
			return true;
		}else{
			return false;
		}
	}
	
	public static String removeStopWords(String string) {
		String result = "";
		String[] words = string.split(" |\n");
		for(String word : words) {
			if(word.isEmpty()) continue;
			if(isStopword(word)) continue; //remove stopwords
			result += (word+" ");
		}
		return result;
	}
	
	public static void main(String[] args){
		//Dan_tri folder
		int count = 0;
		File newsFolder = new File("/home/hieuapp/vitk-output/vtv");
		File[] files = newsFolder.listFiles();
		for( int i = 0; i < files.length; i++){
			if(!files[i].isDirectory()){
				continue;
			}
			//Ban_doc folder
			File labelFolder = files[i];
			String category = labelFolder.getName();
			File bagWordDir = new File("/home/hieuapp/stopword-removal/"+category);
			if(!bagWordDir.exists()){
				bagWordDir.mkdirs();
				System.out.println("Create folder "+category);
			}
			
			File[] news = labelFolder.listFiles();
			for( int j = 0; j < news.length; j++){
				try {
					if(news[j].getName().startsWith(".")){
						continue;
					}
					String content = FileUtils.readFileToString(news[j]);
					content = removeStopWords(content);
					File output = new File(bagWordDir, news[j].getName());
					writeToFile(output, content);
					count++;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Done ! total file = "+count);
		
	}
	
	public static void writeToFile(File outFile, String content){
		if(!outFile.exists()){
            try {
            	outFile.createNewFile();
                System.out.print("Create new file");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        try {
            FileWriter writer = new FileWriter(outFile.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(writer);
            bw.write(content);
            bw.close();
            System.out.println("Done! write file "+outFile.getName());
        } catch (IOException ex) {
        	ex.printStackTrace();
        }
    }

}