package GestionStocke.Service.ServiceImplement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import GestionStocke.DTO.CreditDto;
import GestionStocke.Service.CreditService;
import GestionStocke.entity.Credit;
import GestionStocke.repostory.CreditRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditServiceImplements implements CreditService{
	
	
	private final CreditRepository creditRepository;

	@Override
	public CreditDto save(CreditDto creditDto) {
		
		return null;
	}
	
	@Override
	public CreditDto update(CreditDto creditDto , Integer id) {
		Credit credit =creditRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Credit not found"));
		 credit.setReste(creditDto.getReste());
		creditRepository.save(credit);
		CreditDto dto =CreditDto.fromEntity(credit);
		return dto;
	}


	@Override
	public List<CreditDto> findAll() {
		
		return creditRepository.findAll().stream()
				.map(CreditDto::fromEntity)
				.collect(Collectors.toList());
	}
    
	 /*
	  * true 
	  */
	@Override
	public CreditDto findById(Integer id) {
		
		if(id ==null) {
			throw new RuntimeException("id not found");
		}
	
		
		return creditRepository.findById(id)
				.map(CreditDto::fromEntity)
				.orElseThrow(() -> new RuntimeException("crdit not found bay id "));
	}
	
	
	
	
	@Override
	public void deleteById(Integer id) {
		System.out.println(":::::::::::::::>>>>>>> delete " + id);
		creditRepository.deleteById(id);

	}

	@Override
	public void deleteAll() {
		
		
	}

	@Override
	public List<CreditDto> findAllByClientId(Integer clientId) {
		if(clientId == null) {
			throw new RuntimeException("client not found");
		}
		List<Credit> credits = creditRepository.findByClientId(clientId);
	    List<CreditDto> creditDtos = credits.stream()
	                                        .map(CreditDto::fromEntity)
	                                        .collect(Collectors.toList());
	    return creditDtos ;
	}


	

	
	
	
}
