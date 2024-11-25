package GestionStocke.handler;

import java.util.ArrayList;
import java.util.List;

import GestionStocke.Exception.ErrorCodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ErrorDto {
	 private Integer httpCode;

	  private ErrorCodes code;

	  private String message;

	  private List<String> errors = new ArrayList<>();

}
