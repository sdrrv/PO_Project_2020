// 
// Decompiled by Procyon v0.5.36
// 

package pt.tecnico.po.ui;

import java.io.FileNotFoundException;
import pt.tecnico.po.io.RuntimeEOFException;
import java.io.IOException;
import java.io.EOFException;

final class ErrorMessages
{
    static final String invalidOperation(final String str) {
        return "Opera\u00e7\u00e3o inv\u00e1lida: " + str;
    }
    
    static final String errorEOF(final EOFException obj) {
        return "Fim de entrada de dados (EOF): " + obj;
    }
    
    static final String errorIO(final IOException obj) {
        return "Problema de I/O: " + obj;
    }
    
    static final String errorInvalidNumber(final NumberFormatException ex) {
        return "N\u00famero inv\u00e1lido!";
    }
    
    static final String errorREOF(final RuntimeEOFException obj) {
        return "Fim de entrada de dados (R-EOF): " + obj;
    }
    
    static final String inputError(final FileNotFoundException obj) {
        return "Erro a colocar a entrada de dados: " + obj;
    }
    
    static final String errorClosingInput(final IOException obj) {
        return "Erro a fechar entrada de dados: " + obj;
    }
    
    static final String invalidNumber(final NumberFormatException ex) {
        return "N\u00famero inv\u00e1lido!";
    }
    
    static final String endOfInput() {
        return "Fim do fluxo de dados de entrada";
    }
    
    static final String outputError(final FileNotFoundException obj) {
        return "Erro a colocar a sa\u00edda de dados: " + obj;
    }
    
    static final String logError(final FileNotFoundException obj) {
        return "Erro a especificar o ficheiro de log: " + obj;
    }
    
    private ErrorMessages() {
    }
}
