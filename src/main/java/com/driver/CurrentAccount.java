/*
package com.driver;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

        super(name, balance, 5000);
        this.tradeLicenseId = tradeLicenseId;

        if (balance < 5000)
            throw new Exception("Insufficient Balance");
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        int n = tradeLicenseId.length();
        int temp = 0;

        for (int i = 0; i < n - 1; i++) {
            if (tradeLicenseId.charAt(i) == getTradeLicenseId().charAt(i + 1))
                break;
            else {
                if (tradeLicenseId.charAt(i) != tradeLicenseId.charAt(i + 1))
                    temp++;
            }
        }
        if (temp == n - 1)
            return;

        int freq[] = new int[26];

        for (int i = 0; i < n; i++)
            freq[tradeLicenseId.charAt(i) - 'A']++;

        int max = 0, letter = 0;

        for (int i = 0; i < 26; i++) {
            if (max < freq[i]) {
                max = freq[i];
                letter = i;
            }
        }

        if (max > (n + 1) / 2)
            throw new Exception("valid license can not be generated");

        char ch[] = new char[n];

        // for even position filling

        for (int i = 0; i < n; i += 2) {
            if (freq[letter] > 0) {
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            } else {
                for (int j = 0; j < 26; j++) {
                    if (freq[j] > 0) {
                        letter = j;
                        break;
                    }
                }
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }
        // for odd position filling
        for (int i = 1; i < n; i += 2) {
            if (freq[letter] > 0) {

                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            } else {
                for (int j = 0; j < 26; j++) {
                    if (freq[j] > 0) {
                        letter = j;
                        break;
                    }
                }
                ch[i] = (char) (letter + 'A');
                freq[letter]--;
            }
        }
        tradeLicenseId = String.valueOf(ch);

    }
}
*/

package com.driver;


import java.util.HashMap;
import java.util.Map;
public class CurrentAccount extends BankAccount{
    String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception
        super(name,balance,5000);
        this.tradeLicenseId=tradeLicenseId;
        if(balance<5000) {
            throw  new Exception("Insufficient Balance");


        }
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception

        HashMap<Character,Integer> map=new HashMap<>();
        boolean flag=false;
        String str=tradeLicenseId;
        map.put(str.charAt(0),1);
        for(int i=1; i<str.length(); i++){
            if(str.charAt(i)==str.charAt(i-1)){
                flag=true;
            }
            map.put(str.charAt(i),map.getOrDefault(str.charAt(i),0)+1);
        }

        int mapSize=map.size();

        if(flag){
            for(Character key:map.keySet()){
                if( mapSize%2==1 && map.get(key)>(mapSize/2)+1){
                    throw new Exception("Valid License can not be generated");
                }else if(mapSize%2==0 && map.get(key)>(mapSize/2)){
                    throw new Exception("Valid License can not be generated");
                }
            }

            char copyLincens[]=new char[str.length()];
            boolean ind0=false;
            int ind=0;

            for(Character ch:map.keySet()){
                int n=map.get(ch);
                int i=0;
                if(ind0==false){
                    for(; i<n; i++){
                        copyLincens[ind]=ch;
                        ind+=2;
                        if(ind>=copyLincens.length){
                            flag=true;
                            ind=1;
                            break;
                        }
                    }
                }
                if(ind0){
                    for(; i<n; i++){
                        copyLincens[ind]=ch;
                        ind+=2;
                        if(ind>=copyLincens.length){
                            break;
                        }
                    }
                }
            }
            this.tradeLicenseId= new String(copyLincens);
        }
    }

}