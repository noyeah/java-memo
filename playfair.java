package playfair;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class playfair {
	public static String inputkey() // Ű �Է� �� �Էµ� Ű�� ���� ���� �Լ�
	{
		Scanner sc = new Scanner(System.in);
		System.out.print("Ű�� �Է��� �ּ��� : ");
		String str = sc.nextLine(); // Ű �Է�
		System.out.println("�Էµ� Ű : "+ str + " �Էµ� Ű�� ����: " + str.length());
		String key = str.replace(" ", ""); // Ű ���� ����
		System.out.println("�������ŵ� ���� : " + key.length());
		return key;
	}
	
	public static List deduplication(String str) // ���ڿ��� �ߺ� ���� �Լ�
	{
		List keylist = new ArrayList(); // �ߺ����ڸ� ���Ž�ų ���ο� ����Ʈ �ۼ�
		char[] chr = str.toCharArray(); // �� ���� ��ҵ��� �迭chr �� ����
		for(int i=0;i<chr.length;i++) 
		{
			keylist.add(chr[i]); // chr �迭 ��ҵ��� keylist�� �߰��Ѵ�. 
			System.out.print(keylist.get(i));
		}
		System.out.println();
		for(int i=0;i<chr.length;i++) // �� ��Һ��� �ߺ��Ǵ� ��ҵ��� ã���� �ߺ��Ǵ� ��ҵ鿡 �����Ǵ� keylist �� ��Ҹ� *���ڷ� ġȯ�Ѵ�.
		{
			for(int j=i+1;j<chr.length;j++)
			{
				if(chr[i] == chr[j])
				{
					System.out.println(chr[i] + " �� �ߺ��� i = " + i +" j = "+ j);	
					keylist.remove(j); // �ߺ���Ҹ� ���ο��Ʈ���� ����
					keylist.add(j,'*'); // ���ŵǾ� ������� ��ǥ�� ��ü��
				}
			}
		}
		Iterator iterator = keylist.iterator(); // ���ͷ����� �߰�
        while (iterator.hasNext()) { // ���ͷ����ͷ� ����� ��ȸ �� ��ǥ ǥ�õǾ��ִ� �ߺ���ҵ� ����
            char element = (char) iterator.next();
            System.out.print(element);
            if(element == '*') // element ������ * �� �ߺ�����ϰ�� ����
            {
            	iterator.remove();
            }
        }
        System.out.println();
        System.out.print("�ߺ� ���� => ");
        for(Object object : keylist) { // for-loop ���� keylist ��ü ��ȸ
            char element = (char) object;
            System.out.print(element);
        }
        System.out.println(" �ߺ� ���ŵ� ���� : " + keylist.size());
		return keylist;
	}
	
	public static String[][] drawboard_ij(List key) // ij���� �׸���
	{
		String[][] board = new String[5][5];
		List strlist = new ArrayList(); // key����Ʈ�� �ִ� ij�� ����� �� ó���� �Ϸ�� ����Ʈ
		
		if(key.contains('i') && key.contains('j')) // key �ȿ� i�� j�� �Ѵ� ���� ���
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				if(element.equals("i") || element.equals("j"))
			    {
			    	if(element.equals("i") && !strlist.contains("ij")) // �տ�ij�� ���� i Ȥ�� j�� ���ð�� ij �� ��ü�Ѵ�
			    	{
			    		element = "ij";
			    	}
			    	if(element.equals("j") && !strlist.contains("ij"))
			    	{
			    		element = "ij";
			    	}
			    	if(strlist.contains("ij") && element.equals("i")) // �տ�ij�� ������� �ڿ� i Ȥ�� j �� ����
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
		
		String[] remainder = new String[25-strlist.size()+1]; // key �� �� �Է��ѵ��� ������ ����
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
				if(knum < strlist.size() && i<5) // key �Է�
        		{
        			board[i][j] = strlist.get(knum).toString();
        			knum +=1;
        		} else if(i<5) // key ���� ������ �Է�
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
	
	public static String[][] drawboard_qz(List key) // qz���� �׸���
	{
		String[][] board = new String[5][5];
		
		List strlist = new ArrayList(); // key����Ʈ�� �ִ� ij�� ����� �� ó���� �Ϸ�� ����Ʈ
		
		if(key.contains('q') && key.contains('z')) // key �ȿ� i�� j�� �Ѵ� ���� ���
		{
			for(int i=0;i<key.size();i++)
			{
				String element = (String) key.get(i).toString();
				if(element.equals("q") || element.equals("z"))
			    {
			    	if(element.equals("q") && !strlist.contains("qz")) // �տ�ij�� ���� i Ȥ�� j�� ���ð�� ij �� ��ü�Ѵ�
			    	{
			    		element = "qz";
			    	}
			    	if(element.equals("q") && !strlist.contains("qz"))
			    	{
			    		element = "qz";
			    	}
			    	if(strlist.contains("qz") && element.equals("q")) // �տ�ij�� ������� �ڿ� i Ȥ�� j �� ����
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
		
		String[] remainder = new String[25-strlist.size()+1]; // key �� �� �Է��ѵ��� ������ ����
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
				if(knum < strlist.size() && i<5) // key �Է�
        		{
        			board[i][j] = strlist.get(knum).toString();
        			knum +=1;
        		} else if(i<5) // key ���� ������ �Է�
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
	
	public static List inputplain() // ��ȣȭ�� ���� �Է�
	{
		Scanner sc = new Scanner(System.in);
		List chlist = new ArrayList(); // char[2] ���� ������ ����Ʈ
		System.out.printf("��ȣȭ�� ������ �Է��� �ּ��� : ");
		String ch2 = "";
		// ch2 = "be careful for assassinator";
		ch2 = sc.nextLine();
		ch2 = ch2.replace(" ", ""); // ���� ����
		char[] charr = ch2.toCharArray(); // �ϳ��� ���� ����
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
	
	public static int[] findaddress(String[][] table, char element) // ���̺��� ��Һ��ּҸ�ã����
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
	
	public static List keyaddresslist(String[][] board, List plain) // �򹮵��� ��Һ� ��ǥ���� list �� ����
	{
		List addresslist = new ArrayList();
		for(int i=0;i<plain.size();i++) // listC �� ��ҵ��� ���忡���� ��ǥ���� listD���� ã�ƴٰ� �����Ѵ�.
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
	
	public static List encrypt(List addresslist) // �� �ּҰ��� ��ȣȭ�� ���빮�� �ּҰ����� ��ȯ => ���� ���� ���� å ��� 
	{
		List result = new ArrayList();
		for(int i=0;i<addresslist.size();i++)
        {
        	
        	int[] adnum = new int[4];
        	int[] keyaddress = (int[]) addresslist.get(i);
        	if(keyaddress[0] != keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1��° ������ �ٸ��� �ٸ����� ���
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
        	else if(keyaddress[0] != keyaddress[2] && keyaddress[1] == keyaddress[3]) // 1��° ������ �ٸ��� �������� ���
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
        	else if(keyaddress[0] == keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1��° ������  ������ �ٸ����� ���
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

	public static List encrypt2(List addresslist) // �� �ּҰ��� ��ȣȭ�� ���빮�� �ּҰ����� ��ȯ  => ��Ű���� ���� ��� 
	{
		List result = new ArrayList();
		for(int i=0;i<addresslist.size();i++)
        {
        	
        	int[] adnum = new int[4];
        	int[] keyaddress = (int[]) addresslist.get(i);
        	if(keyaddress[0] != keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1��° ������ �ٸ��� �ٸ����� ��� -> ���� �״�� �ΰ� ���� ���� �ּҸ� �ٲ�
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
        	else if(keyaddress[0] != keyaddress[2] && keyaddress[1] == keyaddress[3]) // 1��° ������ �ٸ��� �������� ���
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
        	else if(keyaddress[0] == keyaddress[2] && keyaddress[1] != keyaddress[3]) // 1��° ������  ������ �ٸ����� ���
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
	
	public static String[] build_encrypt(String[][] board, List encrypt_adlist) // ��ȣȭ�� �ּҰ��� ���� ������ ��ȯ�ؼ� String �迭�� ����
	{
		String[] encrypt_plain = new String[encrypt_adlist.size()];
		
		String temp ="";
    	int bcount = 0;
        Iterator itadlist = encrypt_adlist.iterator(); // ���ͷ����� �߰�
        while (itadlist.hasNext()) 
        { // ���ͷ����ͷ� ����� ��ȸ �� ��ǥ ǥ�õǾ��ִ� �ߺ���ҵ� ����
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
        System.out.print(" ��ȣȭ�� ����� ");
        for(int i=0;i<encrypt_plain.length;i++)
        {
        	System.out.printf(" %s ", encrypt_plain[i]);
        }
        System.out.println();

		return encrypt_plain;
	}

	public static String[] play()
	{
		String ch = inputkey(); // Ű�� �Է¹ް� ������ �����ؼ� ch �� ����
		List keylist = deduplication(ch); // �Էµ� ���ڿ��� �ߺ� ��Ҹ� �����ؼ� keylist �� ����
		String[][] ijboard = drawboard_ij(keylist);
		//String[][] qzboard = drawboard_qz(keylist);
		List plainlist = inputplain();
		List adplainlist_ij = keyaddresslist(ijboard, plainlist); // ���� ��ҵ��� ��ǥ��
		//List adplainlist_qz = keyaddresslist(qzboard, plainlist); // ���� ��ҵ��� ��ǥ��
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
