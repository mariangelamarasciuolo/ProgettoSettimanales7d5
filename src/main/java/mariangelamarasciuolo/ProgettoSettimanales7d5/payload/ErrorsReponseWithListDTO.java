package mariangelamarasciuolo.ProgettoSettimanales7d5.payload;

import java.util.Date;
import java.util.List;

public record ErrorsReponseWithListDTO(String message, Date timeStamp, List<String> errorList) {
}
