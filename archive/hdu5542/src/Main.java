/**
 * Mar 17, 2016 10:16:51 PM
 * PrjName: hdu5542
 * @semprathlon
 */
import java.io.*;
import java.util.*;
public class Main {
	static int[][] f;
	static int[] a,b;
	final static int mod=1000000007;
	static int lowbit(int x) {
        return x & (-x);
    }

    static void add(int y,int p, int v,int sz) {
        while (p <= sz) {
            f[p][y] += v;
            f[p][y]%=mod;
            p += lowbit(p);
        }
    }

    static int sum(int y,int p) {
        int res = 0;
        while (p > 0) {
            res += f[p][y];
            res%=mod;
            p -= lowbit(p);
        }
        return res;
    }
	public static void main(String[] args) throws IOException{
		InputReader in=new InputReader(System.in);
		PrintWriter out=new PrintWriter(System.out);
		int T=in.nextInt(),cas=0;
		while(T-->0){
			int n=in.nextInt();
			int m=in.nextInt();
			a=new int[n+1];
			f=new int[n+1][m+1]; 
			for(int i=1;i<=n;i++)
				a[i]=in.nextInt();
			b=a.clone();
			Arrays.sort(b);
			for(int i=1;i<=n;i++)
				a[i]=Arrays.binarySearch(b, a[i]);
			/*for(int i=1;i<=n;i++)
				out.print(a[i]+" ");
			out.println();*/
			for(int i=1;i<=n;i++){
//				f[i][1]=1;
				add(1, a[i], 1,n);
				for(int j=2;j<=Math.min(i, m);j++){
					int tmp=sum(j-1, a[i]-1);
					add(j, a[i], tmp, n);
					/*for(int k=j-1;k<i;k++)
					if (a[i]>a[k]){
						f[i][j]+=f[k][j-1];
						f[i][j]%=mod;
					}*/
					//f[i][j]%=mod;
				}
			}
			/*for(int i=1;i<=n;i++){
				for(int j=1;j<=m;j++)
					out.print(f[i][j]+" ");
				out.println();
			}*/
			out.println("Case #"+(++cas)+": "+sum(m, n));
		}
		out.flush();
		out.close();
	}

}
class InputReader {
	public BufferedReader reader;
	public StringTokenizer tokenizer;

	public InputReader(InputStream stream) {
		reader = new BufferedReader(new InputStreamReader(stream), 32768);
		tokenizer = null;
	}

	public String nextLine() {
		String tmp = null;
		try {
			tmp = reader.readLine();
			tokenizer = new StringTokenizer(tmp);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NullPointerException e) {
			return null;
		}
		return tmp;
	}

	public String next() {
		while (tokenizer == null || !tokenizer.hasMoreTokens()) {
			try {
				tokenizer = new StringTokenizer(reader.readLine());
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return tokenizer.nextToken();
	}

	public int nextInt() {
		return Integer.parseInt(next());
	}

	public long nextLong() {
		return Long.parseLong(next());
	}

	public double nextDouble() {
		return Double.parseDouble(next());
	}
}
