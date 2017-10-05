package workers;

import net.*;

import java.util.ArrayList;
import java.util.List;

public class SoapWorker {
    public static List<String> checkString(String arg) {
        List<String> result = new ArrayList<String>();
        // Создаём сервис и порт для нужной нам операции.
        SpellService service = new SpellService();
        SpellServiceSoap port = service.getSpellServiceSoap();
        // Формируем объект для отправки типа CheckTextRequest, устанавливая нужные нам поля в нужные значения
        CheckTextRequest request = new CheckTextRequest();
        request.setLang("en");
        request.setText(arg);
        // Отправляем запрос и получаем в ответ CheckTextResponse, откуда достаём нужную нам информацию
        CheckTextResponse checkTextResponse = port.checkText(request);
        List<SpellError> errorList = checkTextResponse.getSpellResult().getError();
        for (SpellError error : errorList) {
            result.add(error.getWord());
        }
        return result;
    }
}