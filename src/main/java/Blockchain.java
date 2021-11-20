// import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Blockchain {
    public List<Block> blockchain;
    private int prefix;
    private String prefixString;

    Blockchain() {
        blockchain = new ArrayList<>();
        prefix = 4;
        prefixString = new String(new char[prefix]).replace('\0', '0');
        Block starterBlock = new Block("First Block",
                "0",
                new Date().getTime());
        starterBlock.mineBlock(prefix);
        blockchain.add(starterBlock);
//        System.out.println("First Block:\nHash: "+starterBlock.getHash()+"\nPrevious Hash: "+starterBlock.getPreviousHash()+"\nData: "+starterBlock.getData()+"\n");
    }

    public void addBlock() {
        Block newBlock = new Block(
                "This is a New Block.",
                blockchain.get(blockchain.size() - 1).getHash(),
                new Date().getTime());
        newBlock.mineBlock(prefix);
        assertTrue(newBlock.getHash().substring(0, prefix).equals(prefixString));
//        System.out.println("Block added:\nHash: "+newBlock.getHash()+"\nPrevious Hash: "+newBlock.getPreviousHash()+"\nData: "+newBlock.getData()+"\n");
        blockchain.add(newBlock);

    }

    public void checkBlockchain() {
        boolean flag = true;
//        System.out.println("\n\n\nChecking\n\n\n");
        for (int i = 0; i < blockchain.size(); i++) {
            System.out.println("Block checked:\nHash: "+blockchain.get(i).getHash()+"\nPrevious Hash: "+blockchain.get(i).getPreviousHash()+"\nData: "+blockchain.get(i).getData()+"\n");
            String previousHash = i==0 ? "0" : blockchain.get(i - 1).getHash();
/*
            if(!blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash()))
                System.out.println("Hash diferente");
            if(!previousHash.equals(blockchain.get(i).getPreviousHash()))
                System.out.println("PreviousHash diferente");
            if(!blockchain.get(i).getHash().substring(0, prefix).equals(prefixString))
                System.out.println("Prefixo diferente");
*/
            flag = blockchain.get(i).getHash().equals(blockchain.get(i).calculateBlockHash())
                    && previousHash.equals(blockchain.get(i).getPreviousHash())
                    && blockchain.get(i).getHash().substring(0, prefix).equals(prefixString);
            if (!flag) break;
        }
        assertTrue(flag);
    }
/*
    public static void main(String[] args){
        Blockchain bc = new Blockchain();
        for(int i=0; i<100; i++)
            bc.addBlock();

        bc.checkBlockchain();
    }

 */
}
