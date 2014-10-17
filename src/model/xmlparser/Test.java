package model.xmlparser;

import model.xmlparser.xmlview.DishAdditionalInfo;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;


public class Test {

    public static void main(String[] args) {
        ArrayList<DishAdditionalInfo> dishAdditionalInfoList = new ArrayList<>();
        InputStream fis = null;
        BufferedReader br = null;
        String line;
        File file = new File("C:/home/oldBase/base/addData/additionalDishInfo.txt");
        try {
            fis = new FileInputStream(file);
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
            int i = 0;
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("﻿", "");
                i++;
                System.out.println(i);
                System.out.println(line);
                String[] elements = line.split("=\\^__\\^=");
                if (elements.length > 0) {
                    Integer parentID = Integer.parseInt(elements[0]);
                    Integer order = Integer.parseInt(elements[1]);
                    String nameEng = elements[2];
                    String nameRus = elements[3];
                    String categoryNameEng = elements[4];
                    String categoryNameRus = elements[5];
                    DishAdditionalInfo dishAdditionalInfo = new DishAdditionalInfo();
                    if (!elements[6].isEmpty()) {
                        Integer catID = Integer.parseInt(elements[6]);
                        dishAdditionalInfo.setCategoryNameEng(categoryNameEng);
                        dishAdditionalInfo.setCategoryNameRus(categoryNameRus);
                        dishAdditionalInfo.setCatID(catID);
                    }
//                    System.out.println(line);
                    String price = elements[7];
//                    System.out.println(price);
                    dishAdditionalInfo.setNameEng(nameEng);
                    dishAdditionalInfo.setNameRus(nameRus);
                    dishAdditionalInfo.setOrder(order);
                    dishAdditionalInfo.setParentID(parentID);
                    dishAdditionalInfo.setPrice(price);
                    dishAdditionalInfoList.add(dishAdditionalInfo);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
