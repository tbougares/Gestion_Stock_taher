package GestionStocke.Service.ServiceImplement;

import org.springframework.stereotype.Service;

import GestionStocke.Service.ModeleVoitureService;
import GestionStocke.entity.ModeleVoiture;
import GestionStocke.repostory.ModeleVoitureRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ModeleVoitureServiceImpl implements ModeleVoitureService {

	
	public final ModeleVoitureRepository modeleVoitureRepository;
	@Override
	public ModeleVoiture save(ModeleVoiture dto) {
		// TODO Auto-generated method stub
		return modeleVoitureRepository.save(dto);
	}

}
