package is.hi.hbv501g.kosmosinn.Kosmosinn.Services.Implementations;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Repositories.BoardRepository;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Services.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BoardServiceImplementation implements BoardService {
    BoardRepository repository;

    @Autowired
    public BoardServiceImplementation(BoardRepository Board) {
        this.repository = Board;
    }

    @Override
    public Board save(Board Board) {
        return repository.save(Board);
    }

    @Override
    public void delete(Board Board) {
        repository.delete(Board);
    }

    @Override
    public List<Board> findAll() {
        return repository.findAll();
    }

    @Override
	public List<Board> findByBoardname(String boardname) {
		return repository.findByBoardname(boardname);
	}

    @Override
    public Optional<Board> findById(long id) {
        return repository.findById(id);
    }
}