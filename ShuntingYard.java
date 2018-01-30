import java.util.*;

class ShuntingYardAlgorithm{
	public String ShuntingYardAlgorithm(String expression){
		//使用StringBuilder构建字符串生成器
		StringBuilder notation = new StringBuilder(expression.length());
		Stack<Character> stack = new Stack<Character>();
		char[] array = expression.toCharArray();
		for(char element: array){
			//记号表示一个数字
			if('0' <= element && element <= '9')
				notation.append(element);
			//记号表示一个操作符
			else if("+-*/".indexOf(element) >= 0){
				if(!stack.empty()){
					while(!isPrior(element, stack.peek())){
						notation.append(stack.peek());
						stack.pop();
						if(stack.empty())
							break;
					}
				}
				stack.push(element);
			}
			//记号表示一个左括号
			else if(element == '(')
				stack.push(element);
			//记号表示一个右括号
			else if(element == ')'){
				if(!stack.empty()){
					while(stack.peek() != '('){
						notation.append(stack.peek());
						stack.pop();
						if(stack.empty())
							break;
					}
				}
				if(!stack.empty())
					stack.pop();
			}
		}//读取记号结束
		//弹出堆栈中的剩余元素
		while(!stack.empty()){
			notation.append(stack.peek());
			stack.pop();
		}
		//由字符串生成器返回一个字符串对象
		return notation.toString();
	}
	//操作符及括号的优先级，数字越小，优先级越高
	public int priorityOf(char operator){
		int priority = 0;
		switch(operator){
			case '(': priority = 4;break;
			case ')': priority = 4;break;
			case '+': priority = 3;break;
			case '-': priority = 3;break;
			case '*': priority = 2;break;
			case '/': priority = 2;break;
		}
		return priority;
	}
	//记号表示一个操作符比较优先级
	//前者优先级低于后者时返回true
	public boolean isPrior(char operator1, char operator2){
		return priorityOf(operator1) < priorityOf(operator2);
	}



	//----------------------------------------
	public int SolveReversePolishNotation(String notation){
		//定义value为结果
		int value = 0;
		Stack<Integer> stack = new Stack<Integer>();
		char[] array = notation.toCharArray();
		for(char element: array){
			//记号表示一个数字
			if('0' <= element && element <= '9')
				stack.push(element - '0');
			//记号表示一个运算符
			else if("+-*/".indexOf(element) >= 0){
				int top = 0, next = 0;
				if(!stack.empty()){
					top = stack.peek();
					stack.pop();
				}
				if(!stack.empty()){
					next = stack.peek();
					stack.pop();
				}
				//进行与运算符对应的运算
				switch(element){
					case '+': top = next + top;break;
					case '-': top = next - top;break;
					case '*': top = next * top;break;
					case '/': top = next / top;break;	
				}
				stack.push(top);
			}
		}
		//弹出栈顶元素作为结果
		if(!stack.empty())
			value = stack.peek();
		return value;
	}

	//----------------------------------------
	
}

public class ShuntingYard{
		public static void main(String[] args){
			ShuntingYardAlgorithm SY = new ShuntingYardAlgorithm();
			String expression = "4+6*5/(2+2+2*1)*7";
			String notation = SY.ShuntingYardAlgorithm(expression);
			System.out.println("notation: " + notation);
			int result = SY.SolveReversePolishNotation(notation);
			System.out.println("result: " + result);
		}
}
