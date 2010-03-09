package macrobug.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import java.awt.*;
import java.util.*;

public class EdiTable extends JComponent implements ChangeCellListener{
	private static final long serialVersionUID = 1L;
	private ArrayList<SimpleTextField> lines;
	private int column,charCell;
	
	public void setText(String s){
		String temp;
		int i;
		for(i=0;i<lines.size()&&s.length()>0;i++){
			temp=s.substring(0,charCell);
			lines.get(i).setText(temp);
			s=s.substring(charCell);
		}
		lines.get(i).requestFocusInWindow();
	}
	
	public String getText(){
		StringBuilder s=new StringBuilder();
		for(SimpleTextField t:lines)
			s.append(t.getText());
		return s.toString();
	}
	
	public void setSelect(int i){
		lines.get(i).select(0,1);
		lines.get(i).requestFocusInWindow();
	}
	
	public int getSelect(){
		for(int i=0;i<lines.size();i++)
			if(lines.get(i).hasFocus())
				return i;
		return -1;
	}
	
	public void fireCellEmptied(ChangeCellEvent event) {
		ChangeCellListener array[]=listenerList.getListeners(ChangeCellListener.class);
		if(lines.indexOf((SimpleTextField)event.getSource())%column==0&&lines.indexOf((SimpleTextField)event.getSource())!=0)
			fireRowEmptied(event);
		else
			for(ChangeCellListener ccl:array)
				ccl.cellEmptied(event);
    }
	public void fireCellFulled(ChangeCellEvent event){
		ChangeCellListener array[]=listenerList.getListeners(ChangeCellListener.class);
		if(lines.indexOf((SimpleTextField)event.getSource())%column==column-1)
			fireRowFulled(event);
		for(ChangeCellListener ccl:array)
			ccl.cellFulled(event);
    }
	public void fireRowEmptied(ChangeCellEvent event){
		ChangeCellListener array[]=listenerList.getListeners(ChangeCellListener.class);
		for(ChangeCellListener ccl:array)
			ccl.rowEmptied(event);
    }
	public void fireRowFulled(ChangeCellEvent event){
		ChangeCellListener array[]=listenerList.getListeners(ChangeCellListener.class);
		for(ChangeCellListener ccl:array)
			ccl.rowFulled(event);
    }
	public void addChangeCellListener(ChangeCellListener ccl){listenerList.add(ChangeCellListener.class, ccl);}
	public void removeChangeCellListener(ChangeCellListener ccl){listenerList.remove(ChangeCellListener.class,ccl);}
	public ChangeCellListener[] getChangeCellListeners(){return listenerList.getListeners(ChangeCellListener.class);}
	
	private void refresh(){
		removeAll();
		Box b[]=new Box[(int)Math.ceil(lines.size()/column)];
		for(int i=0;i<b.length;i++)
			b[i]=new Box(BoxLayout.X_AXIS);
		for(int i=0;i<b.length;i++)
			for(int ì=0;ì<column;ì++)
				b[i].add(lines.get(ì+(i*column)));
		for(int i=0;i<b.length;i++)
			add(b[i]);
		Rectangle r=getBounds();
		if(getFont()!=null)
		setBounds(r.x,r.y,r.width,r.height+2*getFont().getSize());
	}
	
	public EdiTable(int column,int charCell){
		lines=new ArrayList<SimpleTextField>();
		this.column=column;
		this.charCell=charCell;
		setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
		addChangeCellListener(this);
		addLine();
	}
	private void addLine(){
		for(int i=0;i<column;i++)
			lines.add(new SimpleTextField(charCell));
		refresh();
	}
	private void removeLine(SimpleTextField stf){
		int index=lines.indexOf(stf);
		for(int i=0;i<column;i++)
			lines.remove(index);
		refresh();
	}
	private class SimpleTextField extends JTextField implements DocumentListener{
		private static final long serialVersionUID = 2L;
		public SimpleTextField(int c){super(c);getDocument().addDocumentListener(this);setMaximumSize(new Dimension(getFont().getSize()*c,2*getFont().getSize()));}
		@Override
		public void paintBorder(Graphics g){
		}
		public void insertUpdate(DocumentEvent arg0) {
			if(arg0.getDocument().getLength()>=getColumns())
				((EdiTable)getParent().getParent()).fireCellFulled(new ChangeCellEvent(this));
		}
		public void removeUpdate(DocumentEvent arg0) {
			if(arg0.getDocument().getLength()<=0)
				((EdiTable)getParent().getParent()).fireCellEmptied(new ChangeCellEvent(this));
		}
		public void changedUpdate(DocumentEvent arg0){}
	}
	class ChangeCellEvent extends EventObject {

		public ChangeCellEvent(SimpleTextField arg0) {
			super(arg0);
		}

		private static final long serialVersionUID = 4L;

	}
	public void cellEmptied(ChangeCellEvent e){
		SimpleTextField stf=(SimpleTextField)e.getSource();
		int i=lines.indexOf(stf);
		i=(i>0)?i-1:i;
		lines.remove(stf);
		lines.add(new SimpleTextField(charCell));
		refresh();
		setSelect(i);
	}
	public void cellFulled(ChangeCellEvent e){
		setSelect(lines.indexOf((SimpleTextField)e.getSource())+1);
	}
	public void rowEmptied(ChangeCellEvent e){
		int i=lines.indexOf((SimpleTextField)e.getSource());
		removeLine((SimpleTextField)e.getSource());
		setSelect(i-1);
	}
	public void rowFulled(ChangeCellEvent e) {
		addLine();
	}
	
	@Override public void setEnabled(boolean b){
		for(SimpleTextField stf:lines)
			stf.setEnabled(b);
	}
	@Override public boolean isEnabled(){
		return lines.get(0).isEnabled();
	}
	@Override public Color getForeground(){
		return lines.get(0).getForeground();
	}
	@Override public void setForeground(Color c){
		for(SimpleTextField stf:lines)
			stf.setForeground(c);
	}
	@Override public Color getBackground(){
		return lines.get(0).getBackground();
	}
	@Override public void setBackground(Color c){
		for(SimpleTextField stf:lines)
			stf.setBackground(c);
	}
	 @Override public void setFont(Font f){
		 for(SimpleTextField stf:lines)
				stf.setFont(f);
	 }
	 @Override public Font getFont(){
		 return lines.get(0).getFont();
	 }
	public boolean isEditable(){
		return lines.get(0).isEditable();
	}
	public void setEditable(boolean b){
		for(SimpleTextField stf:lines)
			stf.setEditable(b);
	} 
	public static void main(String s[]){
		JFrame f=new JFrame("Asa");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final EdiTable in=new EdiTable(16,2),out=new EdiTable(16,2);
		out.setEditable(false);
		f.getContentPane().add(new JPanel(){
			public JPanel get(){
				setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
				add(new JScrollPane(in));
				add(new JScrollPane(out));
				return this;}
		}.get());
		f.setSize(100,300);
		f.setVisible(true);
	}
}