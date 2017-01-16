/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jeu2048;

import java.util.HashSet;
import java.util.Random;

/**
 *
 * @author oneiroi
 */
public class Grille implements Parametres {
    private HashSet<Case> grille;
    private int valeurMax;
    
    public Grille(){
        this.grille=new HashSet<>();
        valeurMax=0;
    }

    public HashSet<Case> getGrille() {
        return grille;
    }

    public int getValeurMax() {
        return valeurMax;
    }
    
    public String toString(){
        String str;
        str="La taille est: "+this.grille.size()+"La valeur max de la grille est "+this.valeurMax+"\n"+this.getCases();
        
        return str;
    }
    
    public String getCases(){
        String str="";
            for(int j=0;j>-4;j--){
                str+="["+this.getCase(0,j).getValeur();
                for(int i=1;i<4;i++){
                    str+=","+this.getCase(i,j).getValeur();
                }
                str+="]\n";
            }
            
            
        
        return str;
    }
    
    public void setFusionOff(){
        for(int j=0;j>-4;j--){
                this.getCase(0,j).setFusion(false);
                for(int i=1;i<4;i++){
                    this.getCase(i,j).setFusion(false);
                }
            }
    }
    
    public Case getCase(int a,int o){
        Case cF=new Case(a,o,0);
        for(Case c :this.grille){
            if(c.getX()==a&&c.getY()==o){
                cF= c;
            }
        }
        return cF;
    }
    
    public void victory(){
    
        System.out.println("Vous avez gagné : "+this.getValeurMax());
        System.exit(0);
    }
    
    public void gameOver(){
        System.out.println("Vous avez perdu : "+this.getValeurMax());
        System.exit(0);
    }
    
    public boolean nouvelleCase(){
        boolean b=false;
        Random ra=new Random();
        int valeurNC;
        if(grille.size()<(taille*taille)){
            
            valeurNC=(2*(1+ra.nextInt(2)));
            int a = ra.nextInt(4);
            int o = (-1)*ra.nextInt(4);
            Case c=new Case(a,o,valeurNC);
            c.setMaGrille(this);
            while(!b){
                boolean b1=false;
                for(Case c1 : this.grille){
                    if(c.egals(c1)){
                        b1=true;
                    }
                }
                if(!b1){
                    this.grille.add(c);
                    for(Case c1 : grille){
                        if(c1.getValeur()>this.valeurMax){
                            this.valeurMax = c1.getValeur();
                        }
                    }
                    b=true;
                }else{
                    a = ra.nextInt(4);
                    o = (-1)*ra.nextInt(4); 
                    c.setX(a);
                    c.setY(o);
                }
            }
        }
        
        for(Case c1:grille){
            for(Case c2:grille){
                if(c1.egals(c2)){
                    if(c1.getValeur()<c2.getValeur()){
                        grille.remove(c1);
                    }else{
                        if(c1.getValeur()>c2.getValeur()){
                            grille.remove(c2);
                        }
                        
                    }
                }
            }
        }
        
        
        return b;
    }
    
    public boolean partieFinie(){
        boolean b=true;
        
        if(this.grille.size()==(taille*taille)){
            
            for(Case c : this.grille){
                int i =gauche;
                Case c1;
                while(i<3&&b){
                    c1=c.getVoisinDirect(i);
                    
                    if(c1!=null){
                        System.out.println(c.toString()+"\n"+i+" : "+c1.toString());
                        if(c.valeurEgale(c1)){
                            b=false;
                        }
                            
                    }else{
                        if(!(c.getX()==0||c.getX()==taille-1||c.getY()==0||c.getY()==-taille+1)){
                        b=false;
                            System.out.println("bien ou quoi ?");
                        }
                        
                    }
                    i++;
                    if(i==0){
                        i++;
                    }
                }
            }
        }else{
            b=false;
        }
        return b;
    }
    
    public void seDeplacer(int d){
        
        for(Case c1:grille){
            for(Case c2:grille){
                if(c1.egals(c2)){
                    if(c1.getValeur()<c2.getValeur()){
                        grille.remove(c1);
                    }else{
                        if(c1.getValeur()>c2.getValeur()){
                            grille.remove(c2);
                        }
                    }
                }
            }
        }
        
        Case c;
        switch(d){
            case haut:
                for(int i=0;i<taille;i++){
                    for(int j=-1;j>-taille;j--){
                        if(this.getCase(i, j).getValeur()!=0){
                            c=this.getCase(i, j);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                c.getVoisinDirect(d).setFusion(true);
                                
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setY(0);
                                }else{
                                    if(c.getY()!=(c.getVoisinDirect(d).getY()-1)){
                                        c.setY(c.getVoisinDirect(d).getY()-1);
                                    }
                                }
                                grille.add(c);
                            }
                            
                            
                            
                        }
                        
                    }
                }
                
                break;
            case droite:
                
                for(int i=0;i>-taille;i--){
                    for(int j=taille-2;j>-1;j--){
                        if(this.getCase(j, i).getValeur()!=0){
                            c=this.getCase(j, i);
                            this.grille.remove(c);
                            
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                                
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setX(taille-1);
                                }else{
                                    if(c.getX()!=(c.getVoisinDirect(d).getX()-1)){
                                        c.setX(c.getVoisinDirect(d).getX()-1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                            
                        }
                        

                    }
                }
                
                break;
            case bas:
                
                for(int i=0;i<taille;i++){
                    for(int j=(-taille)+2;j<1;j++){
                        if(this.getCase(i, j).getValeur()!=0){
                            c=this.getCase(i, j);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setY(-taille+1);
                                }else{
                                    if(c.getY()!=(c.getVoisinDirect(d).getY()+1)){
                                        c.setY(c.getVoisinDirect(d).getY()+1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                        }
                    }
                }
                break;
            case gauche:
                
                for(int i=0;i>-taille;i--){
                    for(int j=1;j<taille;j++){
                        if(this.getCase(j, i).getValeur()!=0){
                            c=this.getCase(j, i);
                            this.grille.remove(c);
                            if(c.valeurEgale(c.getVoisinDirect(d))&&!c.getVoisinDirect(d).isFusion()){
                                c.getVoisinDirect(d).setValeur(c.getVoisinDirect(d).getValeur()*2);
                                c.getVoisinDirect(d).setFusion(true);
                                if(c.getVoisinDirect(d).getValeur()>this.valeurMax){
                                    this.valeurMax = c.getVoisinDirect(d).getValeur();
                                }
                            }else{
                                if(c.getVoisinDirect(d)==null){
                                    c.setX(0);
                                }else{
                                    if(c.getX()!=(c.getVoisinDirect(d).getX()+1)){
                                        c.setX(c.getVoisinDirect(d).getX()+1);
                                    }

                                }
                                grille.add(c);
                            }
                            
                            
                        }
                    }
                }
                break;
        }
        this.setFusionOff();
        
    }
    private void fusion(Case c){
        c.setValeur(c.getValeur()*2);
        if(c.getValeur()>this.valeurMax){
            this.valeurMax = c.getValeur();
        }
    }
    
    public Case[] getCasesExtremites(int direction){
        Case[] c= new Case[4];
        switch(direction){
            case haut:
                for(int i=0;i<taille;i++){
                        c[i]=this.getCase(i, 0).getVoisinDirect(-direction);
                }
                break;
            case droite:
                for(int i=0;i>-taille;i--){
                    c[-i]=this.getCase(taille-1, i).getVoisinDirect(-direction);
                }    
                break;
            case bas:
                for(int i=0;i<taille;i++){
                    c[i]=this.getCase(i,-taille+1).getVoisinDirect(-direction);
                }
                break;
            case gauche:
                for(int i=0;i>-taille;i--){
                    c[-i]=this.getCase(0,i).getVoisinDirect(-direction);
                }
                break;   
             
        }
        return c;
    }
    
    public boolean lanceurDeplacerCases(int direction){
        boolean b=false;
        switch(direction){
            case haut:
                break;
            case droite:
                break;
            case bas:
                break;
            case gauche:
                break;
        }
        return b;
    }
    
    private void deplacerCasesRecursif(Case[] extremites, int rangee, int direction, int compteur){
        for(int i=0; i<extremites.length;i++){
            this.grille.remove(extremites[i]);
            if(extremites[i].getVoisinDirect(direction)==null){
                                extremites[i].setY(-taille+1);
                            }else{
                                if(extremites[i].getY()!=(extremites[i].getVoisinDirect(direction).getY()+1)){
                                    extremites[i].setY(extremites[i].getVoisinDirect(direction).getY()+1);
                                }
                                
                            }
            this.grille.add(extremites[i]);
        }
    }
}
