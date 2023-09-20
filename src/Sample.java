import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Sample {
    public static void main(String[] args) {
    	
    	inputpoint();
    }

    	private static void inputpoint()
    	{
    		
    		boolean blnFlag = true;
    		Scanner sc = new Scanner(System.in);
	    	File file = new File("C:\\pleiades\\2023-06\\workspace\\sample\\src\\data.txt");
         	try
        	{
        		while (blnFlag) {
        			System.out.println("実施したい処理を選択してください");
        			System.out.println("1:評価ポイントとコメントを入力する");
        			System.out.println("2:今までの結果を確認する");
        			System.out.println("3:終了する");
         			String num = sc.nextLine();
        			switch (num)
        			{
        				case "1":
        					
        					inputEvaluationComment(sc,file);
        					
        					break;
        				case "2":
        					outputEvaluationComment(sc,file);
        					
  
        					break;
        				case "3":
        					// 3が入力されたときの処理
        					System.out.println("終了します");
        					blnFlag = false;
        					break;
        				default:
        					// 1~3以外が入力されたときの処理
        					System.out.println("1から3で入力してください");
        					break;
        			}
        		}
        		
       	}
        	catch (NumberFormatException e) {
        			System.out.println("int型へは変換できません。");
        	}
        	sc.close();
    	}
    	
    	/***********************************************************
    	 * 評価とコメントの入力を行いファイルに出力する
    	 * @param sc	入力用Scannerインスタンス
    	 * @param file	ファイル出力用Fileインスタンス
    	 ***********************************************************/
    	private static void inputEvaluationComment(Scanner sc, File file)
    	{
    		List<String> list = new ArrayList<String>();
    		
			while(true)
			{
	    		System.out.println("1から5で評価を入力してください");
    			String input = sc.nextLine();
    		
    			if (input.matches("[+-]?\\d*(\\.\\d+)?"))
    			{
    				int point = Integer.parseInt(input);
    			
    				if(point < 1 || point > 5)
    				{
    					System.out.println("1から5で入力してください");
    				}
    				else
    				{
    			
    					System.out.println( "コメントを入力してください");
    					String comment = sc.nextLine();
				
    					HashMap<String,String> map = new HashMap<>();
    					map.put("point", Integer.toString(point));		//ポイント
    					map.put("comment", comment);					//コメント
				
    					try 
						{
							list.clear();
					
							String writestr="ポイント：" + map.get("point") + ", " +
											"コメント：" + map.get("comment") + "\r\n";
					
							FileWriter filewriter = new FileWriter(file,true);
							filewriter.write(writestr);
		    	
							filewriter.close();
						}
		    			catch(FileNotFoundException e)
						{
		    				System.out.print(e);
		        		}
						catch(IOException e)
	    				{
							System.out.println(e);
		        		}
    					break;
    				}

    			} else
    			{
    				System.out.println("数字を入力してください");
    			}
			}
    	}
    	
    	/***********************************************************
    	 * 今まで登録された評価とコメントを出力する
    	 * @param sc	入力用Scannerインスタンス
    	 * @param file	ファイル出力用Fileインスタンス
    	 ***********************************************************/
    	private static void outputEvaluationComment(Scanner sc, File file)
    	{
    		List<String> list = new ArrayList<String>();
			list.clear();
   		
			try 
			{
				
	        	    FileReader fileReader = new FileReader(file);
	                BufferedReader bufferedReader = new BufferedReader(fileReader);
	                String text;
	                while ((text = bufferedReader.readLine()) != null)
	                {
	                    list.add(text);
	                }
	                bufferedReader.close();
	                fileReader.close();
	             						
					
    				list.forEach(val ->  System.out.println(val));
	    	}
	    	catch(FileNotFoundException e)
    		{
	    		System.out.print(e);
	        }
			catch(IOException e)
    		{
	            System.out.println(e);
	        }
    		
    	}
    	
        // 引数で受け取った文字列が数値かどうか正規表現でチェックするメソッド
        public static boolean checkString(String text) {
    		
            boolean res = true;
    		
            Pattern pattern = Pattern.compile("^[0-9]+$|-[0-9]+$");
            res = pattern.matcher(text).matches();
    		
            return res;
        }

}