<<<<<<< HEAD
package is.hi.hbv501g.kosmosinn.Kosmosinn.Services;
import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board save(Board board);
    void delete(Board board);
    List<Board> findAll();
    List<Board> findByBoardname(String boardname);
    Optional<Board> findById(long id);
}
=======
package is.hi.hbv501g.kosmosinn.Kosmosinn.Services;

import is.hi.hbv501g.kosmosinn.Kosmosinn.Entities.Board;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    Board save(Board board);
    void delete(Board board);
    List<Board> findAll();
    Optional<Board> findById(long id);
    Board findByName(String name);
}
>>>>>>> origin/oskar_branch
