/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.Jeu2048.model;

/**
 *
 * @author oneiroi
 */
public interface Parametres {
    final int  haut=1;
    final int  bas=-1;
    final int gauche=-2;
    final int droite=2;
    
    final String UP="w";
    final String DOWN="s";
    final String LEFT="a";
    final String RIGHT="d";
    
    final int taille=4;
    final int objectif=2048;
}
