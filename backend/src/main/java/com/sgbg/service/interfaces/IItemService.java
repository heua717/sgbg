package com.sgbg.service.interfaces;

import com.sgbg.domain.Item;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IItemService {
	List<Item> list();
	List<Item> getByUser(int uid);
	Item get(long id);

	@Transactional
	Item register(Item item);

	@Transactional
	Item update(Item item);

	@Transactional
	Item delete(long id);
}