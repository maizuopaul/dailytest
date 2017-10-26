package com.touna.leeo.pattern.strategy;


public class Client {
	public static void main(String args[]) {
		AbstractStrategy strategy = new StrategyA();
		Context context = new Context(strategy);
		context.algorithm();
	}
}
