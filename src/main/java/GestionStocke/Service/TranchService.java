package GestionStocke.Service;

import java.util.List;

import GestionStocke.DTO.TranchDto;

public interface TranchService {

	TranchDto save(TranchDto tranchDto, Integer creditId);

	TranchDto update(Integer id, TranchDto tranchDto);
	public TranchDto findById(Integer id);
	public void deleteById(Integer id);
	List<TranchDto>  findByCreditId(Integer creditId);
}
