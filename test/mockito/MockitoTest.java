package mockito;

import org.junit.*;
import java.util.*;
import play.test.*;
import models.*;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.InOrder;

public class MockitoTest extends UnitTest {

    @Test
    public void mockitoWorksTest() {
	//creacion de mock
	List mockedList = mock(List.class);

	//utilizando el mock object
	mockedList.add("one");
	mockedList.clear();

	//verificacion
	verify(mockedList).add("one");
	verify(mockedList).clear();
    }

    @Test
    public void mockitoWorksTest2() {
	//se pueden hacer mock de clases concretas, no solo interfaces
	LinkedList mockedList = mock(LinkedList.class);

	//stubbing
	when(mockedList.get(0)).thenReturn("first");
	when(mockedList.get(1)).thenThrow(new RuntimeException());

	//imprime "first"
	System.out.println(mockedList.get(0));

	//lanza runtime exception
	System.out.println(mockedList.get(1));

	//imprime "null" porque no se ha hecho stubbing de get(999)
	System.out.println(mockedList.get(999));

	verify(mockedList).get(0);
    }

    @Test
    public void mockitoWorksTest3() {
        //se pueden hacer mock de clases concretas, no solo interfaces
        LinkedList mockedList = mock(LinkedList.class);

	//stubbing usando anyInt() argument matcher
	when(mockedList.get(anyInt())).thenReturn("element");
 
	//stubbing usando hamcrest (libreria de matchers) (digamos que isValid() devuelve tu propio matcher):
//	when(mockedList.contains(argThat(isValid()))).thenReturn("element");
 
	//imprime "element"
	System.out.println(mockedList.get(999));
 
	//tambien se puede verificar usando argument matchers
	verify(mockedList).get(anyInt());
    }

    @Test
    public void mockitoWorksTest4() {
	 List firstMock = mock(List.class);
	 List secondMock = mock(List.class);
 
	 //using mocks
	 firstMock.add("was called first");
	 secondMock.add("was called second");
 
	 //create inOrder object passing any mocks that need to be verified in order
	 InOrder inOrder = inOrder(firstMock, secondMock);
 
	 //following will make sure that firstMock was called before secondMock
	 inOrder.verify(firstMock).add("was called first");
	 inOrder.verify(secondMock).add("was called second");
    }

    @Mock
    LinkedList mockedListAnnotation;

    @Test
    public void mockitoWorksTest5() {
	MockitoAnnotations.initMocks(LinkedList.class);
    }

}
