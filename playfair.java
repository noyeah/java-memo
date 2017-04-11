package playfair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class playfair {
	public static String inputkey() // 키 입력 과 입력된 키의 공백 제거 함수
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("키를 입력해 주세요 : ");
		String str = sc.nextLine(); // 키 입력
		System.out.println("입력된 키 : "+ str + " 입력된 키의 길이: " + str.length());
		String key = str.replace(" ", ""); // 키 공백 제거
		System.out.println("공백제거된 길이 : " + key.length());
		return key;
	}
	
	public static List deduplication(String str) // 문자열의 중복 제거 함수
	{
		List keylist = new ArrayList(); // 중복문자를 제거시킬 새로운 리스트 작성
		char[] chr = str.toCharArray(); // 각 문자 요소들을 배열chr 에 저장
		for(int i=0;i<chr.length;i++) 
		{
			keylist.add(chr[i]); // chr 배열 요소들을 keylist에 추가한다. 
			System.out.print(keylist.get(i));
		}
		System.out.println();
		for(int i=0;i<chr.length;i++) // 각 요소별로 중복되는 요소들을 찾은후 중복되는 요소들에 대응되는 keylist 의 요소를 *문자로 치환한다.
		{
			for(int j=i+1;j<chr.length;j++)
			{
				if(chr[i] == chr[j])
				{
					System.out.println(chr[i] + " 가 중복됨 i = " + i +" j = "+ j);	
					keylist.remove(j); // 중복요소를 새로운리스트에서 제거
					keylist.add(j,'*'); // 제거되어 빈공간을 별표로 대체함
				}
			}
		}
		Iterator iterator = keylist.iterator(); // 이터레이터 추가
        while (iterator.hasNext()) { // 이터레이터로 전요소 조회 와 별표 표시되어있는 중복요소들 제거
            char element = (char) iterator.next();
            System.out.print(element);
            if(element == '*') // element 변수가 * 인 중복요소일경우 삭제
            {
            	iterator.remove();
            }
        }
        System.out.println();
        System.out.print("중복 제거 => ");
        for(Object object : keylist) { // for-loop 통한 keylist 전체 조회
            char element = (char) object;
            System.out.print(element);
        }
        System.out.println(" 중복 제거된 길이 : " + keylist.size());
		return keylist;
	}
	
	public static String[][] drawboard_ij(List key) // ij보드 그리기
	{
		String[][] board = new String[5][5];
		List strlist = new ArrayList(); // key리스트에 있는 ij의 경우의 수 처리가 완료된 리스트
		
		if(key.contains('i') && key.contains('j')) // key 안에 i와 j가 둘다 있을 경우
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				if(element.equals("i") || element.equals("j"))
			    {
			    	if(element.equals("i") && !strlist.contains("ij")) // 앞에ij가 없고 i 혹은 j가 나올경우 ij 로 대체한다
			    	{
			    		element = "ij";
			    	}
			    	if(element.equals("j") && !strlist.contains("ij"))
			    	{
			    		element = "ij";
			    	}
			    	if(strlist.contains("ij") && element.equals("i")) // 앞에ij가 있을경우 뒤에 i 혹은 j 를 생략
			    	{
			    		continue;
			    	}
			    	if(strlist.contains("ij") && element.equals("j"))
			    	{
			    		continue;
			    	}
			    }
			    strlist.add(element);
			    System.out.print(element + " ");
			}
		}
		else
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				strlist.add(element);
			    System.out.print(element);
			}
		}
		
		String[] remainder = new String[25-strlist.size()+1]; // key 값 을 입력한뒤의 나머지 값들
		int inputcount = 0;
		boolean overlaptest = false;
		for(char ch='a'; ch<='z'; ch++)
		{
			overlaptest = false;
			for(int i=0;i<strlist.size();i++)
			{
				String temp = (String) strlist.get(i);
				char[] ctemp = temp.toCharArray();
				for(int x=0;x<ctemp.length;x++)
				{
					if(ch == ctemp[x])
					{
						overlaptest = true;
					}
				}
			}
			
			if(!overlaptest)
			{
				String temp  = new Character(ch).toString();
				if(strlist.contains("i") && temp.equals("j"))
				{
					continue;
				}
				if(strlist.contains("j") && temp.equals("i"))
				{
					continue;
				}
				if((!strlist.contains("i")&&!strlist.contains("j")) && (temp.equals("i")))
				{
					temp = "ij";
					remainder[inputcount] = temp;
					inputcount++;
					ch+=1;
				}
				else
				{
					remainder[inputcount] = temp;
					inputcount++;
				}
			}
		}
		for(int j=0;j<remainder.length;j++)
		{
			System.out.print(remainder[j]);
		}
		System.out.println();
		
		int knum = 0;
		int anum = 0;
		
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(knum < strlist.size() && i<5) // key 입력
        		{
        			board[i][j] = strlist.get(knum).toString();
        			knum +=1;
        		} else if(i<5) // key 이후 나머지 입력
        		{
        			board[i][j] = remainder[anum];
        			anum +=1;
        		}
				System.out.printf(" " + board[i][j] + " ");
			}
			System.out.println();
		}
	return board;
	}
	
	public static String[][] drawboard_qz(List key) // qz보드 그리기
	{
		String[][] board = new String[5][5];
		
		List strlist = new ArrayList(); // key리스트에 있는 ij의 경우의 수 처리가 완료된 리스트
		
		if(key.contains('q') && key.contains('z')) // key 안에 i와 j가 둘다 있을 경우
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				if(element.equals("q") || element.equals("z"))
			    {
			    	if(element.equals("q") && !strlist.contains("qz")) // 앞에ij가 없고 i 혹은 j가 나올경우 ij 로 대체한다
			    	{
			    		element = "qz";
			    	}
			    	if(element.equals("q") && !strlist.contains("qz"))
			    	{
			    		element = "qz";
			    	}
			    	if(strlist.contains("qz") && element.equals("q")) // 앞에ij가 있을경우 뒤에 i 혹은 j 를 생략
			    	{
			    		continue;
			    	}
			    	if(strlist.contains("qz") && element.equals("q"))
			    	{
			    		continue;
			    	}
			    }
			    strlist.add(element);
			    System.out.print(element + " ");
			}
		}
		else
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				strlist.add(element);
			    System.out.print(element);
			}
		}
		
		String[] remainder = new String[25-strlist.size()+1]; // key 값 을 입력한뒤의 나머지 값들
		int inputcount = 0;
		boolean overlaptest = false;
		for(char ch='a'; ch<='z'; ch++)
		{
			overlaptest = false;
			for(int i=0;i<strlist.size();i++)
			{
				String temp = (String) strlist.get(i);
				char[] ctemp = temp.toCharArray();
				for(int x=0;x<ctemp.length;x++)
				{
					if(ch == ctemp[x])
					{
						overlaptest = true;
					}
				}
			}
			
			if(!overlaptest)
			{
				String temp  = new Character(ch).toString();
				if(strlist.contains("q") && temp.equals("z"))
				{
					continue;
				}
				if(strlist.contains("z") && temp.equals("q"))
				{
					continue;
				}
				if((!strlist.contains("q")&&!strlist.contains("z")) && (temp.equals("q")))
				{
					temp = "qz";
					remainder[inputcount] = temp;
					inputcount++;
				}
				else
				{
					remainder[inputcount] = temp;
					inputcount++;
				}
			}
		}
		for(int j=0;j<remainder.length;j++)
		{
			System.out.print(remainder[j]);
		}
		System.out.println();
		
		int knum = 0;
		int anum = 0;
		
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<5;j++)
			{
				if(knum < strlist.size() && i<5) // key 입력
        		{
        			board[i][j] = strlist.get(knum).toString();
        			knum +=1;
        		} else if(i<5) // key 이후 나머지 입력
        		{
        			board[i][j] = remainder[anum];
        			anum +=1;
        		}
				System.out.printf(" " + board[i][j] + " ");
			}
			System.out.println();
		}
		
		
		return board;
	}
	
	public static List inputplain() // 암호화할 내용 입력
	{
		Scanner sc = new Scanner(System.in);
		List chlist = new ArrayList(); // char[2] 개씩 저장할 리스트
		System.out.printf("암호화할 내용을 입력해 주세요 : ");
		String ch2 = "";
		// ch2 = "be careful for assassinator";
		ch2 = sc.nextLine();
		ch2 = ch2.replace(" ", ""); // 공백 제거
		char[] charr = ch2.toCharArray(); // 하나씩 문자 나눔
		for(int i=0;i<ch2.length();i+=2)
		{
			char[] temp = new char[2];
			temp[0]=charr[i];
			if(ch2.length()-1 == i)
			{
				temp[1]='x';
			}
			else
			{
				temp[1]=charr[i+1];
			}
			
			if(temp[0] == temp[1])
			{
				temp[1]='x';
				i-=1;
			}
			chlist.add(temp);
			//System.out.println("chlist : "+ temp[0]+temp[1] + "  ");
		}
		return chlist;
	}
	
	public static int[] findaddress(String[][] table, char element) // 테이블에서 요소별주소를찾아줌
	{
		int[] address = new int[4];
		int count = 0;
		for(int i=0;i<table.length;i++)
		{
			for(int j=0;j<table[0].length;j++)
			{
				char[] temp = table[i][j].toCharArray();
				for(int x=0;x<temp.length;x++)
				{
					if(temp[x] == element)
					{
						address[count]=i;
						address[count+1]=j;
						count+=2;
					}
				}
			}
		}
		return address;
	}
	
	public static List keyaddresslist(String[][] board, List plain) // 평문들의 요소별 좌표값을 list 에 저장
	{
		List addresslist = new ArrayList();
		for(int i=0;i<plain.size();i++) // listC 의 요소들의 보드에서의 좌표값을 listD에서 찾아다가 저장한다.
        {
        	char[] temp = (char[]) plain.get(i);
        	System.out.println(" temp0 " + temp[0] + " temp1 " +temp[1]);
        	int[] adnum = new int[4];
        	for(int j=0;j<2;j++)
        	{
        		adnum[j] = findaddress(board, temp[0])[j];
        		System.out.printf("%d",adnum[j]);
        	}
        	System.out.print(" ");
        	for(int j=2;j<4;j++)
        	{
        		adnum[j] = findaddress(board, temp[1])[j-2];
        		System.out.printf("%d",adnum[j]);
        	}
        	System.out.println();
        	addresslist.add(adnum);
        }
		return addresslist;
	}
	
	public static List encrypt(List addresslist) // 평문 주소값을 암호화된 내용문의 주소값으로 변환 => 정보 보안 개론 책 기반 
	{
		List result = new ArrayList();
		for(int i=0;i<addresslist.size();i++)
        {
        	
        	int[] adnum = new int[4];
        	int[] keyaddress = (int[]) addresslist.get(i);
        	if(keyaddress[0] != keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1번째 보드의 다른행 다른열일 경우
        	{
        		adnum[0] = keyaddress[2];
        		adnum[1] = keyaddress[1];
        		adnum[2] = keyaddress[0];
        		adnum[3] = keyaddress[3];
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	else if(keyaddress[0] != keyaddress[2] && keyaddress[1] == keyaddress[3]) // 1번째 보드의 다른행 같은열일 경우
        	{
        		adnum[1] = keyaddress[1];
        		adnum[3] = keyaddress[3];
        		adnum[0] = keyaddress[0]+1;
        		adnum[2] = keyaddress[2]+1;
        		if(adnum[0] == 5)
        		{
        			adnum[0] = 0;
        		}
        		if(adnum[2] == 5)
        		{
        			adnum[2] = 0;
        		}
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	else if(keyaddress[0] == keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1번째 보드의  같은행 다른열일 경우
        	{
        		adnum[0] = keyaddress[0];
        		adnum[2] = keyaddress[2];
        		adnum[1] = keyaddress[1]+1;
        		adnum[3] = keyaddress[3]+1;
        		if(adnum[1] == 5)
        		{
        			adnum[1] = 0;
        		}
        		if(adnum[3] == 5)
        		{
        			adnum[3] = 0;
        		}
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	result.add(adnum);
        	System.out.println();
        }
		return result;
	}

	public static List encrypt2(List addresslist) // 평문 주소값을 암호화된 내용문의 주소값으로 변환  => 위키페디아 내용 기반 
	{
		List result = new ArrayList();
		for(int i=0;i<addresslist.size();i++)
        {
        	
        	int[] adnum = new int[4];
        	int[] keyaddress = (int[]) addresslist.get(i);
        	if(keyaddress[0] != keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1번째 보드의 다른행 다른열일 경우 -> 행은 그대로 두고 서로 열의 주소를 바꿈
        	{
        		adnum[0] = keyaddress[0];
        		adnum[1] = keyaddress[3];
        		adnum[2] = keyaddress[2];
        		adnum[3] = keyaddress[1];
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	else if(keyaddress[0] != keyaddress[2] && keyaddress[1] == keyaddress[3]) // 1번째 보드의 다른행 같은열일 경우
        	{
        		adnum[1] = keyaddress[1];
        		adnum[3] = keyaddress[3];
        		adnum[0] = keyaddress[0]+1;
        		adnum[2] = keyaddress[2]+1;
        		if(adnum[0] == 5)
        		{
        			adnum[0] = 0;
        		}
        		if(adnum[2] == 5)
        		{
        			adnum[2] = 0;
        		}
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	else if(keyaddress[0] == keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1번째 보드의  같은행 다른열일 경우
        	{
        		adnum[0] = keyaddress[0];
        		adnum[2] = keyaddress[2];
        		adnum[1] = keyaddress[1]+1;
        		adnum[3] = keyaddress[3]+1;
        		if(adnum[1] == 5)
        		{
        			adnum[1] = 0;
        		}
        		if(adnum[3] == 5)
        		{
        			adnum[3] = 0;
        		}
        		for(int j=0;j<4;j++)
            	{
            		System.out.printf("%d",adnum[j]);
            	}
        	}
        	result.add(adnum);
        	System.out.println();
        }
		return result;
	}
	
	public static String[] build_encrypt(String[][] board, List encrypt_adlist) // 암호화된 주소값을 실제 값으로 변환해서 String 배열에 저장
	{
		String[] encrypt_plain = new String[encrypt_adlist.size()];
		
		String temp ="";
    	int bcount = 0;
        Iterator itadlist = encrypt_adlist.iterator(); // 이터레이터 추가
        while (itadlist.hasNext()) 
        { // 이터레이터로 전요소 조회 와 별표 표시되어있는 중복요소들 제거
            int[] element = (int[]) itadlist.next();
            for(int i=0;i<element.length;i+=2)
            {
            	System.out.printf(" %d element = %d%d %s ",i,element[i],element[i+1], board[element[i]][element[i+1]]);
            	if(i == 0)
            	{
            		temp = board[element[i]][element[i+1]];
            	}
            	else if(i == 2)
            	{
            		temp += board[element[i]][element[i+1]];
	            	System.out.println("temp = "+ temp);
	            	encrypt_plain[bcount] = temp;
	        		bcount++;
            	}
            }
        }
        System.out.print(" 암호화된 문장들 ");
        for(int i=0;i<encrypt_plain.length;i++)
        {
        	System.out.printf(" %s ", encrypt_plain[i]);
        }
        System.out.println();

		return encrypt_plain;
	}

	public static String[] play()
	{
		String ch = inputkey(); // 키를 입력받고 공백을 제거해서 ch 에 저장
		List keylist = deduplication(ch); // 입력된 문자열의 중복 요소를 제거해서 keylist 에 저장
		String[][] ijboard = drawboard_ij(keylist);
		//String[][] qzboard = drawboard_qz(keylist);
		List plainlist = inputplain();
		List adplainlist_ij = keyaddresslist(ijboard, plainlist); // 평문의 요소들의 좌표값
		//List adplainlist_qz = keyaddresslist(qzboard, plainlist); // 평문의 요소들의 좌표값
		List encryptlist_ij = encrypt2(adplainlist_ij);
		//List encryptlist_qz = encrypt(adplainlist_qz);
		String[] encrypt_result_ij = build_encrypt(ijboard, encryptlist_ij);
		//String[] encrypt_result_qz = build_encrypt(qzboard, encryptlist_qz);
		return encrypt_result_ij;
	}
	
	public static void main(String[] args) {
		play();
	}

}
