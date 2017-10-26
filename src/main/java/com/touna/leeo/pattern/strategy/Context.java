package com.touna.leeo.pattern.strategy;

public class Context {
	private AbstractStrategy strategy;

	public Context(AbstractStrategy strategy) {
		super();
		this.strategy = strategy;
	}

	public AbstractStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(AbstractStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void algorithm(){
		this.strategy.algorithm();
	}
}
