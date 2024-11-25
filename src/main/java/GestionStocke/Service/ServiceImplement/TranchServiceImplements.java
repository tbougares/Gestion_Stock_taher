package GestionStocke.Service.ServiceImplement;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import GestionStocke.Exception.EntityNotFoundException;
import GestionStocke.entity.Credit;
import GestionStocke.entity.Tranch;
import GestionStocke.repostory.CreditRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import GestionStocke.DTO.TranchDto;
import GestionStocke.Service.TranchService;
import GestionStocke.repostory.TranchRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TranchServiceImplements implements TranchService{
	
	private final TranchRepository tranchRepository;
	private final CreditRepository creditRepository ;

	@Override
	@Transactional
	public TranchDto save(TranchDto tranchDto, Integer creditId) {
		Credit credit =creditRepository.findById(creditId)
				.orElseThrow(() ->  new EntityNotFoundException("Credit id " + creditId + " not found"));
		Tranch tranch = TranchDto.toEntity(tranchDto);
		validateTranchAmount(tranch, credit);
		tranch.setCredit(credit);
		tranch.setDateDePaiement(LocalDate.now());
		credit.setReste(credit.getReste() - tranch.getMontantAPayee());
		if(credit.getReste()== 0 ) {  
			credit.getCommandeClient().setStatus(true);
			credit.getCommandeClient().setContientCredit(false);
		}
		creditRepository.save(credit);
		return TranchDto.fromEntity(tranchRepository.save(tranch));
	}

// validation de montant de tranch
	private void validateTranchAmount(Tranch tranch, Credit credit) {
		if (credit.getReste() < tranch.getMontantAPayee()) {
			throw new IllegalArgumentException("Le montant de la tranche est supérieur au reste du crédit.");
		}
	}

	@Override
	public TranchDto update(Integer id, TranchDto tranchDto) {
       Tranch tranch = tranchRepository.findById(id)
			   .orElseThrow();
	   tranch.setMontantAPayee(tranchDto.getMontantAPayee());
	   tranchRepository.save(tranch);
        return TranchDto.fromEntity(tranch);
	};
	@Override
	public TranchDto findById(Integer id) {
		if(id==null){
		throw new RuntimeException("id not found");
		}
		return TranchDto.fromEntity(tranchRepository.findById(id).get());
	}
	@Override
	public void deleteById(Integer id) {
     tranchRepository.deleteById(id);
	}

	@Override
	public List<TranchDto> findByCreditId(Integer creditId) {
	  List<TranchDto>	 tranches = tranchRepository.findByCreditId(creditId)
			  .stream()
			  .map(TranchDto::fromEntity)
			  .collect(Collectors.toList());
	   return tranches ;
			   

	}


}
