package GestionStocke.Service.ServiceImplement;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.MvtStkDto;
import GestionStocke.Service.MvtStkService;

@Service
public class MvtStkServiceImplements implements MvtStkService {

	@Override
	public BigDecimal stockReelArticle(Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MvtStkDto> mvtStkArticle(Integer idArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MvtStkDto entreeStock(MvtStkDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MvtStkDto sortieStock(MvtStkDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MvtStkDto correctionStockPos(MvtStkDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MvtStkDto correctionStockNeg(MvtStkDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
