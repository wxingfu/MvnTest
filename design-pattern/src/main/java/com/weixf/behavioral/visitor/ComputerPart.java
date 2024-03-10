package com.weixf.behavioral.visitor;

public interface ComputerPart {

    void accept(ComputerPartVisitor computerPartVisitor);

}
