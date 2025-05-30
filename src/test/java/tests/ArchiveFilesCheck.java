package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ArchiveFilesCheck {

    private ClassLoader cl = ArchiveFilesCheck.class.getClassLoader();


    @Test
    @DisplayName("Проверка чтения PDF файла из Архива")
    void checkReadingPDFFromArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("SOS.zip");
             ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    System.out.println("Found PDF file: " + entry.getName());
                    PDF pdf = new PDF(zip);
                    Assertions.assertEquals("branchNumber:0000", pdf.text);
//                    String processedExpected = "branchNumber:0000".toLowerCase().trim();
//                    String processedActual = pdf.text.toLowerCase().trim();
//                    Assertions.assertEquals(processedExpected, processedActual);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка чтения XLSX файла из Архива")
    void checkReadingXLSXFromArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("SOS.zip");
             ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx")) {
                    System.out.println("Found .xlsx file: " + entry.getName());
                    XLS xlsx = new XLS(zip);
                    String actualValue = xlsx.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue();
                    Assertions.assertEquals("La", actualValue);
                }
            }
        }
    }

    @Test
    @DisplayName("Проверка чтения CSV файла из Архива")
    public void checkReadingCSVFromArchive() throws Exception {
        try (InputStream is = cl.getResourceAsStream("SOS.zip");
             ZipInputStream zip = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zip.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    System.out.println("Found .csv file: " + entry.getName());
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zip));
                    List<String[]> data = csvReader.readAll();
                    Assertions.assertEquals(3, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"Файл", " раз"},
                            data.get(0)
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"файл", " двас"},
                            data.get(1)
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"Файл", " трис"},
                            data.get(2)
                    );
                }
            }
        }
    }
}
