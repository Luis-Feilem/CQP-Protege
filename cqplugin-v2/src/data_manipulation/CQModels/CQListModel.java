package data_manipulation.CQModels;

import java.util.Enumeration;

import javax.swing.AbstractListModel;
import javax.swing.JList;

import data_storage.CompetencyQuestion;
import data_storage.CompetencyQuestionsSet;

/**
 * ListModel with its Vector attribute declared  static.
 * It also includes a static JList<CompetencyQuestion> that serves as meeting point for the views 
 * that use this model and that, actually, is the entry point to the data_storage module.
 * @author Luis
 *
 */
public class CQListModel extends AbstractListModel<CompetencyQuestion> {

	private static final long serialVersionUID = 6705358431031801070L;
	private static CompetencyQuestionsSet delegate = new CompetencyQuestionsSet();
	
	//This is one cheap workaround to skip the communication
	private static JList<CompetencyQuestion> idsList;
	
	
	/**
	 * Get the static JList<CompetencyQuestion> of the model.
	 * @return idsList - JList<CompetencyQuestion>
	 */
	public JList<CompetencyQuestion> getIdsList() {
		return idsList;
	}

	/**
	 * Set the static JList<CompetencyQuestion> of the model.
	 * @param idsList - JList<CompetencyQuestion>
	 */
	public void setIdsList(JList<CompetencyQuestion> idsList) {
		CQListModel.idsList = idsList;
	}

	@Override
	public CompetencyQuestion getElementAt(int index) {
		// TODO Auto-generated method stub
		return delegate.elementAt(index);
	}

	
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return delegate.size();
	}

	/**
	 * @see java.util.Vector#copyInto(Object[] anArray)
	 */
	public void copyInto(CompetencyQuestion[] anArray){
		delegate.copyInto(anArray);
	}
	
	/**
	 * @see java.util.Vector#trimToSize()
	 */
	public void trimToSize(){
		delegate.trimToSize();
	}
	
	/**
	 * @see java.util.Vector#ensureCapacity(int minCapacity)
	 */
	public void ensureCapacity(int minCapacity){
		delegate.ensureCapacity(minCapacity);
	}
	
	/**
	 * @see java.util.Vector#setSize(int newSize)
	 */
	public void setSize(int newSize){
		int oldSize =  delegate.size();
		delegate.setSize(newSize);;
		if(oldSize > newSize){
			fireIntervalRemoved(this, newSize, oldSize-1);
		}
		else if (oldSize<newSize){
			fireIntervalAdded(this, oldSize, newSize-1);
		}
	}
	
	/**
	 * @see java.util.Vector#capacity()
	 */
	public int capacity(){
		return delegate.capacity();
	}
	
	/**
	 * @see java.util.Vector#size()
	 */
	public int size(){
		return delegate.size();
	}
	
	/**
	 * @see java.util.Vector#isEmpty()
	 */
	public boolean isEmpty(){
		return delegate.isEmpty();
	}
	
	/**
	 * @see java.util.Vector#elements()
	 */
	public Enumeration<CompetencyQuestion> elements(){
		return delegate.elements();
	}

	/**
	 * @see java.util.Vector#contains(Object o)
	 */
	public boolean contains(CompetencyQuestion cq){
		return delegate.contains(cq);
	}
	
	/**
	 * @see java.util.Vector#indexOf(Object o)
	 */
	public int indexOf(CompetencyQuestion elem){
		return delegate.indexOf(elem);
	}
	
	/**
	 * @see java.util.Vector#indexOf(Object o, int index)
	 */
	public int indexOf(CompetencyQuestion elem, int index){
		return delegate.indexOf(elem, index);
	}
	
	/**
	 * @see java.util.Vector#lastIndexOf(Object o)
	 */
	public int lastIndexOf(CompetencyQuestion elem){
		return delegate.lastIndexOf(elem);
	}
	
	/**
	 * @see java.util.Vector#lastIndexOf(Object o, int index)
	 */
	public int lastIndexOf(CompetencyQuestion elem, int index){
		return delegate.lastIndexOf(elem, index);
	}
	
	/**
	 * @see java.util.Vector#elementAt(int index)
	 */
	public CompetencyQuestion elementAt(int index){
		return delegate.elementAt(index);
	}
	
	/**
	 * @see java.util.Vector#firstElement()
	 */
	public CompetencyQuestion firstElement(){
		return delegate.firstElement();
	}
	
	/**
	 * @see java.util.Vector#lastElement()
	 */
	public CompetencyQuestion lastElement(){
		return delegate.lastElement();
	}
	
	/**
	 * @see java.util.Vector#setElementAt(Object o, int index)
	 */
	public void setElementAt(CompetencyQuestion elem, int index){
		delegate.setElementAt(elem, index);
		fireContentsChanged(this, index, index);
	}
	
	/**
	 * @see java.util.Vector#removeElementAt(int index)
	 */
	public void removeElementAt(int index){
		delegate.removeElementAt(index);
		fireIntervalRemoved(this, index, index);
	}
	
	/**
	 * @see java.util.Vector#insertElementAt(Object o, int index)
	 */
	public void insertElementAt(CompetencyQuestion elem, int index){
		delegate.insertElementAt(elem, index);
		fireIntervalAdded(this, index, index);
	}
	
	/**
	 * @see java.util.Vector#addElement(Object o)
	 */
	public void addElement(CompetencyQuestion elem){
		int index = delegate.size();
		delegate.addElement(elem);
		fireIntervalAdded(this, index, index);
	}
	
	/**
	 * @see java.util.Vector#addAll(java.util.Collection)
	 * @param elems
	 */
	public void addAll(CompetencyQuestionsSet elems){
		int index = delegate.size();
		for (int i = 0; i<elems.size(); i++){
			delegate.addElement(elems.elementAt(i));
		}
		fireIntervalAdded(this, index, index + elems.size());
	}
	
	/**
	 * @see java.util.Vector#removeElement(Object o)
	 */
	public boolean removeElement(CompetencyQuestion elem){
		int index = indexOf(elem);
		boolean rv = delegate.removeElement(elem);
		if(index >=0){
			fireIntervalRemoved(this, index, index);
		}
		return rv;
	}
	
	/**
	 * @see java.util.Vector#removeAllElements()
	 */
	public void removeAllElements(){
		int index1 = delegate.size()-1;
		delegate.removeAllElements();
		if (index1>=0){
			fireIntervalRemoved(this, 0, index1);
		}
	}
	
	/**
	 * @see java.util.Vector#toString()
	 */
	public String toString(){
		return delegate.toString();
	}
	
	/**
	 * @see java.util.Vector#toArray()
	 */
	public CompetencyQuestion[] toArray(){
		CompetencyQuestion[] rv = new CompetencyQuestion[delegate.size()];
		delegate.copyInto(rv);
		return rv;
	}
	
	/**
	 * @see java.util.Vector#get(int index)
	 */
	public CompetencyQuestion get(int index){
		return delegate.get(index);
	}
	
	/**
	 * @see java.util.Vector#set(int index, Object o)
	 */
	public CompetencyQuestion set(int index, CompetencyQuestion elem){
		CompetencyQuestion rv = delegate.elementAt(index);
		delegate.setElementAt(elem,  index);;
		fireContentsChanged(this, index, index);
		return rv;
	}
	
	/**
	 * @see java.util.Vector#add(int index, Object o)
	 */
	public void add(int index, CompetencyQuestion elem){
		delegate.insertElementAt(elem,  index);
		fireIntervalAdded(this, index, index);
	}
	
	/**
	 * @see java.util.Vector#remove(int index)
	 */
	public CompetencyQuestion remove(int index){
		CompetencyQuestion rv = delegate.elementAt(index);
		delegate.removeElementAt(index);;
		fireIntervalRemoved(this, index, index);
		return rv;
	}
	
	/**
	 * @see java.util.Vector#clear()
	 */
	public void clear(){
		int index1 = delegate.size()-1;
		delegate.removeAllElements();
		if(index1>=0){
			fireIntervalRemoved(this, 0, index1);
		}
	}
	
	/**
	 * @see java.util.Vector#removeRange(int fromIndex, int toIndex)
	 */
	public void removeRange(int fromIndex, int toIndex){
		if(fromIndex > toIndex){
			throw new IllegalArgumentException("fromIndex must be <= toIndex");
		}
		for(int i = toIndex; i> fromIndex; i--){
			delegate.removeElementAt(i);;
		}
		fireIntervalRemoved(this, fromIndex, toIndex);
	}
	
	public String getOntology() {
		return delegate.getOntology();
	}

	public void setOntology(String ontology) {
		delegate.setOntology(ontology);
	}

	public String getLanguage() {
		return delegate.getLanguage();
	}

	public void setLanguage(String language) {
		delegate.setLanguage(language);
	}
	
	public void replaceDelegate(CompetencyQuestionsSet substitute){
		clear();
		addAll(substitute);
	}
}
