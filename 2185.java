import java.util.*;
import java.io.*;

public class TransactionClass extends Object {

    private int Id, WalletId, CategoryId;

    private long Amount;

    private Date Date;

    private String Comment;

    public TransactionClass(int wallet_id, int category_id, long amount, Date date, String comment) {
        super();
        Id = 0;
        WalletId = wallet_id;
        CategoryId = category_id;
        Amount = amount;
        Date = date;
        Comment = comment;
    }

    public TransactionClass(int id, byte record[]) throws IOException {
        super();
        DataInputStream data_stream;
        data_stream = new DataInputStream(new ByteArrayInputStream(record));
        Id = id;
        WalletId = data_stream.readInt();
        CategoryId = data_stream.readInt();
        Amount = data_stream.readLong();
        Date = new Date(data_stream.readLong());
        Comment = data_stream.readUTF();
    }

    public int GetId() {
        return Id;
    }

    public int GetWalletId() {
        return WalletId;
    }

    public int GetCategoryId() {
        return CategoryId;
    }

    public long GetAmount() {
        return Amount;
    }

    public Date GetDate() {
        return Date;
    }

    public String GetComment() {
        return Comment;
    }

    public byte[] ToByteArray() throws IOException {
        ByteArrayOutputStream byte_stream;
        DataOutputStream data_stream;
        byte_stream = new ByteArrayOutputStream();
        data_stream = new DataOutputStream(byte_stream);
        data_stream.writeInt(WalletId);
        data_stream.writeInt(CategoryId);
        data_stream.writeLong(Amount);
        data_stream.writeLong(Date.getTime());
        data_stream.writeUTF(Comment);
        return byte_stream.toByteArray();
    }
}
