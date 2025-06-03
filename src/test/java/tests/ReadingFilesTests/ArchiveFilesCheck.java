package tests.ReadingFilesTests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
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
//                    String expected = "weight, paddings, top, zero, left, zero, bottom, zero, right";
//                    Assertions.assertTrue(
//                            pdf.text.replaceAll("\\s+", " ").trim().contains(expected),
//                            "PDF не содержит ожидаемый текст"
//                    );
                    Assertions.assertEquals("weight, paddings, top, zero, left, zero, bottom, zero, right\n", pdf.text);
//                    String processedExpected = "branchNumber:0000".toLowerCase().trim();
//                    String processedActual = pdf.text.toLowerCase().trim();
//                    Assertions.assertEquals(processedExpected, processedActual);
                }
            }
        }
    }
//@Test
//@DisplayName("Проверка чтения PDF файла из Архива")
//    void checkReadingPDFFromArchive() throws Exception {
//        boolean pdfFound = false;
//        String expectedText = "weight, paddings, top, zero, left, zero, bottom, zero, right";
//
//        try (InputStream is = cl.getResourceAsStream("SOS.zip");
//             ZipInputStream zip = new ZipInputStream(is)) {
//            ZipEntry entry;
//
//            while ((entry = zip.getNextEntry()) != null) {
//                if (entry.getName().endsWith(".pdf")) {
//                    pdfFound = true;
//                    System.out.println("Найден PDF: " + entry.getName());
//
//                    try {
//                        // Читаем PDF (предполагается, что PDF класс корректно обрабатывает поток)
//                        PDF pdf = new PDF(zip);
//                        String normalizedText = pdf.text.replaceAll("\\s+", " ").trim();
//                        Assertions.assertTrue(
//                                normalizedText.contains(expectedText),
//                                "PDF не содержит ожидаемый текст: " + expectedText
//                        );
//                    } catch (Exception e) {
//                        Assertions.fail("Ошибка парсинга PDF: " + e.getMessage());
//                    }
//                }
//            }
//        }
//        Assertions.assertTrue(pdfFound, "В архиве не найден PDF-файл");
//    }

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
                    String actualValue = xlsx.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue(); //если getRow 1, поиск
                    // работает, если 2 -Invalid XLS
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

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zip, StandardCharsets.UTF_8));
                    List<String[]> data = csvReader.readAll();
//                    Assertions.assertEquals(3, data.size());
                    Assertions.assertArrayEquals(
                            new String[] {"Файл", " раз"},
                            data.get(0),
                            "Несоответствие в первой строке"
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"файл", " двас"},
                            data.get(1),
                            "Несоответствие во второй строке"
                    );
                    Assertions.assertArrayEquals(
                            new String[]{"Файл", " трис"},
                            data.get(2),
                            "Несоответствие в третьей строке"
                    );
                }
            }
        }
    }
}
