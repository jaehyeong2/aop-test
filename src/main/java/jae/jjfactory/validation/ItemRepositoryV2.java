package jae.jjfactory.validation;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepositoryV2 {

    private static Map<Long,Item> store = new HashMap<>();
    private static long sequence = 0l;

    public Item save(Item item){
        item.setId(++sequence);
        store.put(sequence,item);
        return item;
    }

    public Item findById(Long itemId){
        return store.get(itemId);
    }

    public List<Item> findAll(){
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, jae.jjfactory.login.domain.item.Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearStore() { store.clear();}

}
