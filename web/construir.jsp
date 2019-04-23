<?xml version='1.0' encoding='UTF-8' ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<ui:decorate xmlns="http://www.w3.org/1999/xhtml"
             xmlns:h="http://xmlns.jcp.org/jsf/html"
             xmlns:p="http://primefaces.org/ui"
             xmlns:ui="http://xmlns.jcp.org/jsf/facelets"
             template="template.xhtml"
             xmlns:f="http://xmlns.jcp.org/jsf/core">

     <ui:define name="centro">

            <h:form enctype="multipart/form-data" id="envia">
                    <h:panelGrid columns="4" cellpadding="5">  
                        <h:outputLabel for="name" value="Validador" style="font-weight:bold"/>  
                        <p:inputText id="name" value="#{pprBean.firstname}" />  
                        <p:commandButton value="Submit" update="display"/>  
                        <h:outputText value="#{pprBean.firstname}" id="display" />  
                    </h:panelGrid>

                    <p:fileUpload fileUploadListener="#{indexMB.doUploadOdx}" label="ODX..." auto="true" /> 
                    <p:fileUpload fileUploadListener="#{indexMB.doUploadExcel}" label="Requirements Tabel..." auto="true"/> 

                <p:messages severity="info" escape="false" showDetail="true" autoUpdate="true" closable="true"/>

                <p:button outcome="rli" value="NEXT" icon="ui-icon-star"/>    

            </h:form>
        </ui:define>
</ui:decorate>                
                    