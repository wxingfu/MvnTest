package com.wxf.behavioral.responsibility_chain;

/*
 *
 * @author weixf
 * @date 2022-08-23
 */
public abstract class AbstractProcessor implements Processor {

    private Processor next;

    public AbstractProcessor(Processor processor) {
        this.next = processor;
    }

    @Override
    public Processor getNextProcessor() {
        return next;
    }

    @Override
    public abstract void process(String param);
}
