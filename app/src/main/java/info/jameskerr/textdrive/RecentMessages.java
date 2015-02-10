package info.jameskerr.textdrive;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.util.ArrayList;

/**
 * This class is responsible for keeping track of the recent messages
 * that a user has set as their auto-reply messages.
 */
public class RecentMessages implements Serializable {
    static RecentMessages instance = null;
    private int limit = 10;
    private String filename = "RecentMessages.data";
    public ArrayList<String> list;


    /******************************************
     * ::: CONSTRUCTORS & SINGLETON ACCESS ::: *
     ******************************************/

    private RecentMessages() {
        list = new ArrayList<String>();
    }

    public static RecentMessages getInstance() {
        if (instance == null) {
            instance = new RecentMessages();
            return instance;
        } else {
            return instance;
        }
    }

    public void push(String message) {
        list.add(0, message);
        if (list.size() > limit) {
            list.remove(list.size() - 1);
        }
    }

    public void saveList(Context context) {
        ObjectOutputStream object_out;
        FileOutputStream file_out;
        File file;

        try {
            file       = new File(context.getFilesDir(), filename);
            file_out   = new FileOutputStream(file);
            object_out = new ObjectOutputStream(file_out);
            object_out.writeObject(this.list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadList(Context context) {
        ObjectInputStream object_in;
        FileInputStream file_in;
        File file;
        try {
            file      = new File(context.getFilesDir(), filename);
            file_in   = new FileInputStream(file);
            object_in = new ObjectInputStream(file_in);
            this.list = (ArrayList<String>) object_in.readObject();
            object_in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
