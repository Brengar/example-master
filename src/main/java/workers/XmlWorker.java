package workers;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class XmlWorker {

    public static boolean check(List<String> arg) {
        boolean finded = false;
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("src/main/resources/otherxml.xml");
            // Получаем корневой элемент
            Node root = document.getDocumentElement();
            // Просматриваем все подэлементы корневого - т.е. книги
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                Node book = books.item(i);
                // Если нода не текст, то это книга - заходим внутрь
                if (book.getNodeType() != Node.TEXT_NODE) {
                    if (book.getNodeType() != Node.TEXT_NODE) {
                        NodeList bookProps = book.getChildNodes();
                        // Проверяем, нужная-ли нам это книга
                        if (arg.get(1).equals(bookProps.item(1).getTextContent())) {
                            // Указываем, что нужная книга была найдена
                            finded = true;
                            for (int j = 0; j < bookProps.getLength(); j++) {
                                Node bookProp = bookProps.item(j);
                                // Если нода не текст, то это один из параметров книги
                                if (bookProp.getNodeType() != Node.TEXT_NODE) {
                                    // Сравниваем параметры книги
                                    if (!arg.get(j).equals(bookProp.getChildNodes().item(0).getTextContent()))
                                        return false;
                                }
                            }
                        }
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
        return finded;
    }

    public static void write(List<String> arg) {
        try {
            // Создается построитель документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            // Создается дерево DOM документа из файла
            Document document = documentBuilder.parse("src/main/resources/xml.xml");
            // Вызываем метод для добавления новой книги
            addNewBook(document, arg);

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            ex.printStackTrace(System.out);
        }
    }

    private static void addNewBook(Document document, List<String> arg) throws TransformerFactoryConfigurationError, DOMException {
        // Получаем корневой элемент
        Node root = document.getDocumentElement();
        // Создаем новую книгу по элементам
        Element book = document.createElement("Book");
        Element title = document.createElement("Title");
        title.setTextContent(arg.get(0));
        Element author = document.createElement("Author");
        author.setTextContent(arg.get(1));
        Element date = document.createElement("Date");
        date.setTextContent(arg.get(2));
        Element isbn = document.createElement("ISBN");
        isbn.setTextContent(arg.get(3));
        Element publisher = document.createElement("Publisher");
        publisher.setTextContent(arg.get(4));
        Element cost = document.createElement("Cost");
        cost.setTextContent(arg.get(5));
        cost.setAttribute("currency", arg.get(6));
        // Добавляем внутренние элементы книги в элемент <Book>
        book.appendChild(title);
        book.appendChild(author);
        book.appendChild(date);
        book.appendChild(isbn);
        book.appendChild(publisher);
        book.appendChild(cost);
        // Добавляем книгу в корневой элемент
        root.appendChild(book);
        // Записываем XML в файл
        writeDocument(document);
    }

    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try (FileOutputStream fos = new FileOutputStream("src/main/resources/otherxml.xml")) {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

}