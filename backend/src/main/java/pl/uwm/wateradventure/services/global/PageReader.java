package pl.uwm.wateradventure.services.global;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.uwm.wateradventure.models.global.WaterAdventureEntity;

/**
 * Public abstract class created in the needs of preloading data to tables
 * It can be used in reader services to consume getAllSortByPageable method with parameters :
 * @parameter JpaRepository - any jpa repository that uses inheritor of WaterAdventureEntity
 * @parameter offset - specified number of rows before the beginning of the result set
 * @parameter limit - limit the number of rows returned from a query
 * @parameter sortable - parameter which allows to sort by specific field
 * @parameter ascending - for ascending/descending sort
 */
public abstract class PageReader<T extends WaterAdventureEntity> {

    public Page<T> getAllSortedPageable(
            JpaRepository<T, Long> repository,
            String sortable,
            Boolean ascending) {

        Pageable pageable;
        if(ascending) {
            pageable = PageRequest.of(0, 20, Sort.by(sortable).ascending());
        }
        else {
            pageable = PageRequest.of(0, 20, Sort.by(sortable).descending());
        }
        return repository.findAll(pageable);
    }

    // TODO -- maybe add there get with filters method
    //   other readers could consume it from one class

}
