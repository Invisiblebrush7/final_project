package api.encryption;
import java.math.BigInteger;
// (5, 14) -> (e, n)
// (11, 14) -> (d, n)
import java.util.ArrayList;

public class RSA implements Encryption {

    private static RSA instance = null;

    private ArrayList<Character> key;
    private ArrayList<Character> shuffledKey;

    private int p1 = 7;
    private int p2 = 13;
    
    private int n; // this is p1 * p2
    private int t; // this is (p1 - 1) * (p2 - 1)

    private int e; // encryption number -> Must be less than t -> Must be coprime with T and N
    private int d; // decryption number

    // public key is (e, n)
    // private key is (d, n)

    // public String getPublicKey(){
    //     String msg = String.format("Public Key -> (%d, %d)", this.e, this.n);
    //     return msg;
    // }

    // public String getPrivateKey(){
    //     String msg = String.format("Private Key -> (%d, %d)", this.d, this.n);
    //     return msg;
    // }
    public static RSA getInstance(){
        if (RSA.instance == null) {
            System.out.println("Creating a new instance");
            RSA.instance = new RSA();
            return RSA.instance;
        } else {
            System.out.println("Getting the same instance");
            return RSA.instance;
        }
    }

    private RSA(){
        this.key = Encryption.getNewKey();
        this.shuffledKey = Encryption.shuffleKey(this.key);
        setN();
        setT();
        setE();
        setD();
    }

    private void setN(){
        this.n = this.p1 * this.p2;
    }
    private void setT(){
        this.t = (p1-1) * (p2-1); 
    }
    private void setE(){
        // Get a range from N - 1..2 so that we can get the highest num
        for (int i = this.n - 1; i > 0; i--) {
            if ( areCoprime(i, this.t) ) {
                this.e = i;
                break;
            }
        }
    }
    private void setD(){
        // get a list of multiples of E
        // save the list of numbers that comply with (e*d)mod 6 = 1
        for (int i = 1; i < this.e * 20; i++) {
            if ( (this.e * i) % this.t == 1) {
                this.d = i;
                break;
            }
        }
    }

    private boolean areCoprime(int a, int b){
        ArrayList<Integer> aFactors = getFactors(a);
        ArrayList<Integer> bFactors = getFactors(b);
        // ex: a -> [1, 5]
        // ex: b -> [1, 2, 3, 6]
        int hcf = 0;
        for (int factorA : aFactors) {
            for (int factorB : bFactors) {
                if (hcf > 1) {
                    return false;
                } else if (factorA == factorB) {
                    hcf++;
                }
            }
        }
        return true;
    }

    private ArrayList<Integer> getFactors(int num){
        ArrayList<Integer> factors = new ArrayList<Integer>();
        for (int i = 1; i < num; i++) {
            if (num % i == 0) {
                factors.add(i);
            }
        }
        return factors;
    }

    @Override
    public String encryptMessage(String decryptedMessage) {
        // This works by converting the message to an array
        // each letter has an index inside the key array. For example '!' = 1
        // I take that value, or index, an encrypt it with RSA (index**e) mod n
        // that will return a value that fits our array again
        // For example, index = 1 will be now index = 23, just to say something
        // Now, that index = 23 is going to be our new letter by key.get(index)
        // that way, a whole new word is creating using encrypted values
        char[] encryptedMessage = decryptedMessage.toCharArray();
        
        BigInteger nBigInt = BigInteger.valueOf(this.n);

        for (int i = 0; i < encryptedMessage.length; i++) {
            char letter = encryptedMessage[i];
            int index = this.key.indexOf(letter);

            BigInteger encryptionValue = BigInteger.valueOf(index);
            encryptionValue = encryptionValue.pow(this.e);
            encryptionValue = encryptionValue.mod(nBigInt); 
            encryptedMessage[i] = this.key.get(encryptionValue.intValue());
        }
        return String.valueOf(encryptedMessage);
    }
    @Override
    public String decryptMessage(String encryptedMessage) {
        // It's pretty much the same as encryption but instead of (index**e) % n
        // is index**d % n
        BigInteger nBigInt = BigInteger.valueOf(this.n);

        char[] decryptedMessage = encryptedMessage.toCharArray();
        for (int i = 0; i < decryptedMessage.length; i++) {
            char letter = decryptedMessage[i];
            int index = this.key.indexOf(letter);

            BigInteger decryptedValue = BigInteger.valueOf(index);
            decryptedValue = decryptedValue.pow(this.d);
            decryptedValue = decryptedValue.mod(nBigInt);
            decryptedMessage[i] = this.key.get(decryptedValue.intValue());
        }

        return String.valueOf(decryptedMessage);
    }


    public String randomizeMessage(String decryptedMessage){
        decryptedMessage = decryptedMessage.toLowerCase();
        char[] letters = decryptedMessage.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            for (int j = 0; j < this.key.size(); j++) {
                if (letters[i] == this.key.get(j)) {
                    letters[i] = this.shuffledKey.get(j);
                    break;
                }
            }
        }
        return String.valueOf(letters);
    }    
}
