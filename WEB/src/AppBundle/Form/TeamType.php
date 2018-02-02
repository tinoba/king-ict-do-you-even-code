<?php

namespace AppBundle\Form;

use AppBundle\Entity\Team;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\CollectionType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class TeamType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('teamname', TextType::class)
            ->add('password', PasswordType::class)
            ->add('members',CollectionType::class, array(
                'entry_type' => MemberType::class,
                'entry_options' => array('label' => false),
                'allow_add' => true,
                'required' => false,
                'allow_delete' => true,
                'by_reference' => false,
            ))
            ->add('Submit', SubmitType::class);
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults(array(
            'data_class' => Team::class,
        ));
    }

    public function getBlockPrefix()
    {
        return 'app_bundle_team_registration_form';
    }
}
