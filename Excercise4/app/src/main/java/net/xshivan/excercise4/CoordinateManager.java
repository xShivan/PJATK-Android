package net.xshivan.excercise4;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CoordinateManager {
    private static final String FILENAME_COORDS = "coordinates.json";

    private static ArrayList<Coordinates> coordinates = new ArrayList<>();

    public static void store(Coordinates coords, Context context) {
        coordinates.add(coords);
        saveCoordsFile(context);
    }

    public static void clearAll(Context context) {
        coordinates.clear();
        saveCoordsFile(context);
    }

    public static ArrayList<Coordinates> getCoordinates() {
        return coordinates;
    }

    public static void initialize(Context context) {
        File coordsFile = getCoordsFile(context);
        if (!coordsFile.exists())
            return;

        FileInputStream fis;
        try {
            fis = context.openFileInput(coordsFile.getName());
            StringBuffer fileContent = new StringBuffer("");
            byte[] buffer = new byte[1024];
            int n;
            while ((n = fis.read(buffer)) != -1)
                fileContent.append(new String(buffer, 0, n));

            String coordsJson = fileContent.toString();

            Type coordinatesListType = new TypeToken<ArrayList<Coordinates>>(){}.getType();
            Gson gson = new Gson();
            coordinates = gson.fromJson(coordsJson, coordinatesListType);
        } catch (Exception ex) {
            Log.i("ERR", "An error occured while initializing coordinates: " + ex.toString());
        }
    }

    private static void saveCoordsFile(Context context) {
        Gson gson = new Gson();
        String coordsJson = gson.toJson(coordinates);

        File coordsFile = getCoordsFile(context);
        if (coordsFile.exists())
            coordsFile.delete();

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(coordsFile.getName(), Context.MODE_PRIVATE));
            outputStreamWriter.write(coordsJson);
            outputStreamWriter.close();
        }
        catch (Exception ex) {
            Log.i("ERR", "An error occured while storing coordinates: " + ex.toString());
        }
    }

    private static File getCoordsFile(Context ctx) {
        return new File(ctx.getFilesDir(), FILENAME_COORDS);
    }
}
