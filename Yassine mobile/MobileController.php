<?php

namespace App\Controller;

use App\Entity\Produits;
use App\Repository\ProduitRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

class MobileController extends AbstractController
{
    /**
     * @Route("/mobile", name="mobile")
     */
    public function index(): Response
    {
        return $this->render('mobile/index.html.twig', [
            'controller_name' => 'MobileController',
        ]);
    }
    /**
     *  @Route("/liste", name="liste")

     */
    public function getCours(Request $request,ProduitRepository  $produitRepository,NormalizerInterface $normalizer):Response{


$reclamation= $this->getDoctrine()->getManager()->getRepository(Produits::class)->findAll();
       $serializer = new Serializer([new ObjectNormalizer()]);
       $formatted = $serializer->normalize($reclamation);
       return new JsonResponse($formatted);

    }

    /**
     * @Route("add", name="add_produit")
     */
    public function addProduit(Request $request){
        $produit= new Produits();
        $libelle= $request->query->get("libelle");
        $type= $request->query->get("type");
        $quantites= $request->query->get("quantites");
        $prix= $request->query->get("prix");
        $produit->setLibelle($libelle);
        $produit->setType($type);
        $produit->setQuantites($quantites);
        $produit->setPrix($prix);


        $em=$this->getDoctrine()->getManager();
        $em->persist($produit);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("produit Ajoutée");
        return new JsonResponse($formatted);

    }
    /**
     * @Route("/deleteproduit", name="deleteproduit", methods={"GET","POST"})
     *
     */
    public function deleteProduit(Request $request){
        $id=$request->get('id');
        $entityManager = $this->getDoctrine()->getManager();
        $produit=$entityManager->getRepository(Produits::class)->find($id);
        if($produit!=null){
            $entityManager->remove($produit);
            $entityManager->flush();
            $serialize = new Serializer([new ObjectNormalizer()]);
            $formatted = $serialize->normalize("Produit deleted");
            return new JsonResponse($formatted);
        }

    }
    /**
     * @Route("/updateproduit", name="updateproduit", methods={"GET","POST"})
     */
    public function updateProduits(Request $request){
        $em=$this->getDoctrine()->getManager();
        $produit=$this->getDoctrine()->getManager()->getRepository(Produits::class)->find($request->get("id"));
        $produit->setLibelle($request->get("libelle"));
        $produit->setType($request->get("type"));
        $produit->setQuantites($request->get("quantites"));
        $produit->setPrix($request->get("prix"));
        $em->persist($produit);
        $em->flush();
        $serialize = new Serializer([new ObjectNormalizer()]);
        $formatted = $serialize->normalize("Produit updated");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/detail/{id}", name="detailstade")
     *
     */



    public function Produitdetail(Request $request,ProduitRepository $produitRepository,SerializerInterface $serializerinterface,$id)
    {
        $repo = $produitRepository->find($id);
        $json = $serializerinterface->serialize($repo,'json',['groups'=>'produit']);

        return new Response (json_encode($json));

    }
}
