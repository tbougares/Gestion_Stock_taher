package GestionStocke.Service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.MvtStkDto;
@Component
public interface MvtStkService {
	

	 BigDecimal stockReelArticle(Integer idArticle);

	  List<MvtStkDto> mvtStkArticle(Integer idArticle);

	  MvtStkDto entreeStock(MvtStkDto dto);

	  MvtStkDto sortieStock(MvtStkDto dto);

	  MvtStkDto correctionStockPos(MvtStkDto dto);

	  MvtStkDto correctionStockNeg(MvtStkDto dto);

}
