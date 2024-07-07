import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.*;
import java.nio.charset.StandardCharsets;

class com {
  byte a;
  byte b;
  int c;
  public com(byte a,byte b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}

class dec {
  int a;
  int b;
  int c;
  public dec(int a, int b, int c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }
}

public class Main {
  public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String choiseStr;
		String sourceFile, resultFile, firstFile, secondFile;
		
		loop: while (true) {
			
			choiseStr = sc.next();
								
			switch (choiseStr) {
			case "comp":
				System.out.print("source file name: ");
				sourceFile = sc.next();
				System.out.print("archive name: ");
				resultFile = sc.next();
				comp(sourceFile, resultFile);
				break;
			case "decomp":
				System.out.print("archive name: ");
				sourceFile = sc.next();
				System.out.print("file name: ");
				resultFile = sc.next();
				decomp(sourceFile, resultFile);
				break;
			case "size":
				System.out.print("file name: ");
				sourceFile = sc.next();
				size(sourceFile);
				break;
			case "equal":
				System.out.print("first file name: ");
				firstFile = sc.next();
				System.out.print("second file name: ");
				secondFile = sc.next();
				System.out.println(equal(firstFile, secondFile));
				break;
			case "about":
				about();
				break;
			case "exit":
				break loop;
			}
		}

		sc.close();
	}

	public static void comp(String sourceFile, String resultFile) {
    ArrayList<com> arrl = new ArrayList<com>();
    File filer = new File(sourceFile);
		if (filer.exists()) {
      try {
        FileReader f = new FileReader(filer, StandardCharsets.UTF_8);
        int[] a = new int[510];
        for (int i=0; i<a.length; i++) {
          a[i] = f.read();
        }
        int[] b = new int[255];
          loop: while(true) {
            boolean status = false;
            int c = 0;
            Arrays.fill(b, -1);
            for (int i=0; i<b.length; i++) {
              if (a[255] == a[i]) {
                b[c] = i;
                c++;
                status = true;
              }
            }
            if (status) {
              com first = new com((byte)-128, (byte)-128, 0);
              for (int i=0; i<b.length; i++) {
                if (b[i] > -1) {
                  byte d = -128;
                  for (int j=0; j<255; j++) {
                    if (a[255+j] == a[b[i]+j]) {
                      d++;
                    }
                    else {
                      break;
                    }
                  }
                  if (d>first.b) {
                    first.a = (byte)((255-b[i])-128);
                    first.b = d;
                    first.c = a[255+d+128];
                  }
                }
              }
              arrl.add(first);
              for (int i=0; i<a.length-1-((int)first.b+128); i++) {
                a[i] = a[i+1+(int)first.b+128];
              }
              for (int i=0; i<(int)first.b+129;i++) {
                a[a.length-((int)first.b+128)-1+i]=f.read();
                if (a[a.length-((int)first.b+128)-1+i]==-1) {
                  break loop;
                }
              }
            }
            else {
              com first = new com((byte)-128, (byte)-128,a[255]);
              arrl.add(first);
              for (int i=0; i<a.length-1;i++) {
                a[i] = a[i+1];
              }
              a[509] = f.read();
              if (a[509]==-1) {
                break loop;
              }
            }
          }
        while (a[255]!=-1) {
          boolean status = false;
          int c = 0;
          Arrays.fill(b, -1);
          for (int i=0; i<b.length; i++) {
            if (a[255] == a[i]) {
              b[c] = i;
              c++;
              status = true;
            }
          }
          if (status) {
            com first = new com((byte)-128, (byte)-128, 0);
            for (int i=0; i<b.length; i++) {
							if (b[i] > -1) {
                byte d = -128;
                for (int j = 0; j<255; j++) {
                  if (a[255+j]==a[b[i]+j]) {
                    d++;
                  }
                  else {
                    break;
                  }
                }
                if (d>first.b) {
                  first.a = (byte)((255-b[i])-128);
                  first.b = d;
                  first.c = a[255+d+128];
                }
              }
            }
            arrl.add(first);
            for (int i=0; i<a.length-1-((int)first.b+128); i++) {
              a[i] = a[i+1+(int)first.b+128];
            }
            for (int i=0; i<(int)first.b+128+1; i++) {
                a[a.length-((int)first.b+128)-1+i] = -1;
            }
          }
          else {
            com first = new com ((byte)-128, (byte)-128, a[255]);
            arrl.add(first);
            for (int i=0; i<a.length-1; i++) {
              a[i] = a[i+1];
            }
            a[509] = -1;
          }
        }
        f.close();
      }
      catch (IOException ex) {
  			System.out.println(ex.getMessage());
        return;
      }
    }
    else {
      System.out.println("file not found");
      return;
    }
    try {
      Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile), "UTF-8"));
      for (int i=0; i<arrl.size(); i++) {
        out.write(arrl.get(i).a+128);
        out.write(arrl.get(i).b+128);
        out.write(arrl.get(i).c);
      }
      out.close();
    }
    catch (IOException ex) {
  			System.out.println(ex.getMessage());
        return;
    }
	}

	public static void decomp(String sourceFile, String resultFile) {
    ArrayList<dec> arrb = new ArrayList<dec>();
		File filer = new File(sourceFile);
    if (filer.exists()) {
      try {
        FileReader f = new FileReader(filer, StandardCharsets.UTF_8);
        loop: while(true) {
          int a = f.read();
          int b = f.read();
          int c = f.read();
          if (c==-1) {
            break loop;
          }
          dec first = new dec(a,b,c);
          arrb.add(first);
        }
        f.close();
      }
      catch (Exception e) {
        System.out.println(e.getMessage());
        return;
      }
    }
    else {
      System.out.println("file not found");
      return;
    }
    try {
      int[] d = new int[255];
       Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(resultFile), "UTF-8"));
      for (int i=0; i<arrb.size(); i++) {
        if (arrb.get(i).b==0) {
          for (int j=0; j<d.length-1; j++) {
            d[j] = d[j+1];
          }
          d[254] = arrb.get(i).c;
          out.write(d[254]);
        }
        else if (arrb.get(i).c==-1) {
          int e = arrb.get(i).b;
          int[] a = new int[e+1];
          a[e] = arrb.get(i).c;
          a[0] = d[255-arrb.get(i).a];
          if (e>1) {
            for(int  j = 1; j < e; j++) {
							a[j] = d[255-arrb.get(i).a + j];
						}
					}
          for (int j=0; j<d.length-1-e; j++) {
            d[j] = d[j+1+e];
          }
          for (int j=d.length-1-e, k=0; j<d.length-1;j++) {
            d[j] = a[k];
            out.write(a[k]);
            k++;
          }
        }
        else {
          int e = arrb.get(i).b;
          int[] a = new int[e+1];
          a[e] = arrb.get(i).c;
          if (e>1) {
            boolean status = false;
            int q = 0;
            for (int j=1; j<e;j++) {
              if (255-arrb.get(i).a+j>=255) {
                status = true;
                q = j;
                break;
              }
              else {
                a[j] = d[255-arrb.get(i).a+j];
              }
            }
            if (status) {
              for (int j=q; j<e; j++) {
                a[j] = a[j-q];
              }
            }
          }
          for (int j=0; j<d.length-1-e; j++) {
            d[j] = d[j+1+e];
          }
          for (int j=d.length-1-e, k=0; j<d.length; j++) {
            d[j] = a[k];
            out.write(a[k]);
            k++;
          }
        }
      }
      out.close();
    }
    catch (Exception e) {
        System.out.println(e.getMessage());
        return;
    }
	}
	
	public static void size(String sourceFile) {
    File f = new File(sourceFile);
    if (f.exists()) {
  		try {
  			System.out.println("size: " + f.length());
  		}
  		catch (Exception e) {
  			System.out.println(e.getMessage());
  		}
    }
    else {
      System.out.println("file not found");
      return;
    }
	}
	
	public static boolean equal(String firstFile, String secondFile) {
    boolean status = false;
    File filer1 = new File(firstFile);
    File filer2 = new File(secondFile);
    if (filer1.exists() && filer2.exists()) {
      try {
  			FileReader f1 = new FileReader(filer1);
  			FileReader f2 = new FileReader(filer2);
  			int k1, k2;
  			loop: while (true) {
  				k1 = f1.read();
  				k2 = f2.read();
          if (-1 == k1) {
            f1.close();
            f2.close();
            status = true;
            break loop;
          }
  				if (k1 != k2) {
  					f1.close();
  					f2.close();
  					return false;
  				}
        }
  			if (status) {
          return true;
        }
        else {
          return false;
        }
  		}
  		catch (IOException ex) {
  			System.out.println(ex.getMessage());
  			return false;
  		}
    }
    else {
      return false;
    }
	}
	
	public static void about() {
    System.out.println("Emīls Grabis, 15. grupa");
		System.out.println("Krista Lapsiņa-Mazitāne, 15. grupa");
		System.out.println("Evelīna Graumane, 15. grupa");
	}
}