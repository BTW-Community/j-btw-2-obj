package org.jmc.entities;

import org.jmc.NBT.TAG_Compound;
import org.jmc.entities.models.EntityModel;
import org.jmc.threading.ChunkProcessor;

public abstract class Entity {
	
	protected EntityModel model;
	private String id;
	
	public void useModel(String id, EntityModel model) {
		this.model=model;
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getName() {
		return this.id;
	}

	public abstract void addEntity(ChunkProcessor obj, TAG_Compound entity);
	
}
