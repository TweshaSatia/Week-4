package Day1.Practice;

public class MaxChar {
    static final int NO_OF_CHARS = 256;
    /* Print most used character present
      in the passed string */
    static char printMaxChar(String str)
    {
        // Create an array of size
        // 256 and fill count of
        // every character in it
        int count[] = new int[NO_OF_CHARS];
        char result=' ';
        for(int i=0;i<str.length();i++){
            count[str.charAt(i)]++;
        }
        int max=-1;
        for (int i = 0; i < str.length(); i++)
            if (count[str.charAt(i)] > max){
                max=count[str.charAt(i)];
                result=str.charAt(i);
            }
          return result;     
    }
 
    // Driver Method
    public static void main(String[] args)
    {
        String str = "Hashing program";
        System.out.println("String is "+str);
      
        System.out.println("Max occurring character is "+   printMaxChar(str));

    }
}
