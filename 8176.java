import java.lang.String;
import java.io.*;

public class Memory {

    int memory[];

    boolean writeable;

    int dataword_length;

    int size;

    Memory(int size, int dataword_length, boolean writeable) {
        this.size = size;
        this.dataword_length = dataword_length;
        this.writeable = writeable;
        memory = new int[size];
    }

    final int fetch(int address) {
        return memory[address];
    }

    final void store(int address, int data) {
        if (address < size && this.writeable) {
            memory[address] = data;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean load(String filename) {
        try {
            RandomAccessFile mem_file = new RandomAccessFile(filename, "r");
            while (!mem_file.readLine().equals("SYMBOL TABLE")) ;
            long end = mem_file.getFilePointer();
            mem_file.seek(0);
            while (mem_file.getFilePointer() < end) {
                String address = new String();
                String data = new String();
                char tecken = 0;
                if ((char) mem_file.read() == '0') {
                    while (tecken != 32) {
                        tecken = (char) mem_file.read();
                        if (tecken != 32) address += tecken;
                    }
                    while ((tecken = (char) mem_file.read()) == 32) ;
                    mem_file.seek(mem_file.getFilePointer() - 1);
                    do {
                        tecken = (char) mem_file.read();
                        if (tecken != 32) data += tecken;
                    } while (tecken != 32);
                    memory[HexUtils.HexStringToInt(address)] = HexUtils.HexStringToInt(data);
                }
                while (tecken != 0x0A && mem_file.getFilePointer() < mem_file.length()) {
                    tecken = (char) mem_file.read();
                }
            }
            mem_file.close();
        } catch (IOException e) {
            System.out.println("Error reading memory file " + filename);
            return false;
        }
        return true;
    }

    public boolean loadbin(String filename) {
        try {
            RandomAccessFile mem_file = new RandomAccessFile(filename, "r");
            int address = 0;
            while ((mem_file.getFilePointer() < mem_file.length()) && address < 64) {
                memory[address++] = mem_file.readByte();
            }
            mem_file.close();
        } catch (IOException e) {
            System.out.println("Error reading memory file " + filename);
            return false;
        }
        return true;
    }

    public void clear() {
        for (int i = 0; i < size; i++) memory[i] = 0;
    }
}
