/**
 * Copyright (C) SEI, PKU, PRC. - All Rights Reserved.
 * Unauthorized copying of this file via any medium is
 * strictly prohibited Proprietary and Confidential.
 * Written by Jiajun Jiang<jiajun.jiang@pku.edu.cn>.
 */
package cofix.core.parser.node.stmt;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Type;

import cofix.core.metric.CondStruct;
import cofix.core.metric.Literal;
import cofix.core.metric.LoopStruct;
import cofix.core.metric.MethodCall;
import cofix.core.metric.Operator;
import cofix.core.metric.OtherStruct;
import cofix.core.metric.Variable;
import cofix.core.modify.Modification;
import cofix.core.parser.node.Node;
import cofix.core.parser.node.expr.Expr;

/**
 * @author Jiajun
 * @datae Jun 23, 2017
 */
public class ReturnStmt extends Stmt {

	private Expr _expression = null;
	
	private Expr _expression_replace = null;
	
	/**
	 * ReturnStatement:
     *	return [ Expression ] ;
	 */
	public ReturnStmt(int startLine, int endLine, ASTNode node) {
		this(startLine, endLine, node, null);
	}

	public ReturnStmt(int startLine, int endLine, ASTNode node, Node parent) {
		super(startLine, endLine, node, parent);
	}
	
	public void setExpression(Expr expression){
		_expression = expression;
	}
	
	@Override
	public boolean match(Node node, Map<String, Type> allUsableVariables, List<Modification> modifications) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean adapt(Modification modification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean restore(Modification modification) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean backup(Modification modification) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public StringBuffer toSrcString() {
		StringBuffer stringBuffer = new StringBuffer("return ");
		if(_expression_replace != null){
			stringBuffer.append(_expression_replace.toSrcString());
		} else if(_expression != null){
			stringBuffer.append(_expression.toSrcString());
		}
		stringBuffer.append(";");
		return stringBuffer;
	}

	@Override
	public List<Literal> getLiterals() {
		if(_expression != null){
			return _expression.getLiterals();
		}
		return new LinkedList<>();
	}

	@Override
	public List<Variable> getVariables() {
		if(_expression != null){
			return _expression.getVariables();
		}
		return new LinkedList<>();
	}

	@Override
	public List<LoopStruct> getLoopStruct() {
		return new LinkedList<>();
	}
	
	@Override
	public List<CondStruct> getCondStruct() {
		if(_expression != null){
			return _expression.getCondStruct();
		}
		return new LinkedList<>();
	}

	@Override
	public List<MethodCall> getMethodCalls() {
		if(_expression != null){
			return _expression.getMethodCalls();
		}
		return new LinkedList<>();
	}

	@Override
	public List<Operator> getOperators() {
		if(_expression != null){
			return _expression.getOperators();
		}
		return new LinkedList<>();
	}
	
	@Override
	public List<OtherStruct> getOtherStruct() {
		List<OtherStruct> list = new LinkedList<>();
		OtherStruct otherStruct = new OtherStruct(this, OtherStruct.KIND.RETURN);
		list.add(otherStruct);
		return list;
	}
}